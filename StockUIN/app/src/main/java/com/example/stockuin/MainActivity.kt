package com.example.stockuin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnPinjam: Button
    private lateinit var btnKembalikan: Button
    private lateinit var rvBarang: RecyclerView
    private val list = ArrayList<Barang>()
    lateinit var barangAdapter: BarangAdapter
    private var namaUser: String? = null
    private var nimUser: String? = null
    private var isPengembalian: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val riwayatPref = PrefRiwayat.load(this)
        if (riwayatPref.isNotEmpty()) {
            DataGlobal.daftarRiwayat = riwayatPref
        }

        val barangPref = PrefBarang.load(this)

        if (barangPref.isNotEmpty()) {
            DataGlobal.daftarBarang = barangPref
        } else {
            // Pertama kali install, simpan data default ke storage
            PrefBarang.simpan(this, DataGlobal.daftarBarang)
        }

        // Ambil data nama dan nim dari login
        namaUser = intent.getStringExtra("NAMA_USER")
        nimUser = intent.getStringExtra("NIM_USER")

        btnPinjam = findViewById(R.id.btnPinjam)
        btnKembalikan = findViewById(R.id.btnKembalikan)
        rvBarang = findViewById(R.id.rvBarang)

        barangAdapter = BarangAdapter(DataGlobal.daftarBarang)
        rvBarang.layoutManager = LinearLayoutManager(this)
        rvBarang.adapter = barangAdapter

        btnPinjam.setOnClickListener {
            isPengembalian = false // <--- penting
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan QR Code untuk Pinjam Barang")
            integrator.setBeepEnabled(true)
            integrator.setOrientationLocked(true)
            integrator.initiateScan()
        }

        btnKembalikan.setOnClickListener {
            isPengembalian = true // <--- penting
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan QR Code untuk Kembalikan Barang")
            integrator.setBeepEnabled(true)
            integrator.setOrientationLocked(true)
            integrator.initiateScan()
        }

        val navProfile = findViewById<LinearLayout>(R.id.nav_profile)
        navProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("NAMA_USER", namaUser)
            intent.putExtra("NIM_USER", nimUser)
            startActivity(intent)
        }
        val navHome = findViewById<LinearLayout>(R.id.nav_home)
        navHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        val navHistory = findViewById<LinearLayout>(R.id.nav_history)
        navHistory.setOnClickListener {
            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
        }
        val navInventory = findViewById<LinearLayout>(R.id.nav_inventory)
        navInventory.setOnClickListener {
            val intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val qrData = result.contents.trim().lowercase()
            val barang = DataGlobal.daftarBarang.find {
                it.kodeQR.trim().lowercase() == qrData
            }

            if (barang != null) {
                if (isPengembalian) {
                    barang.jumlah += 1
                    PrefBarang.simpan(this, DataGlobal.daftarBarang)
                    Toast.makeText(this, "${barang.nama} berhasil dikembalikan!", Toast.LENGTH_SHORT).show()
                } else {
                    if (barang.jumlah > 0) {
                        barang.jumlah -= 1
                        PrefBarang.simpan(this, DataGlobal.daftarBarang)
                        Toast.makeText(this, "${barang.nama} berhasil dipinjam!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "${barang.nama} sudah habis!", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                val index = DataGlobal.daftarBarang.indexOf(barang)
                if (index >= 0) {
                    barangAdapter.notifyItemChanged(index)
                } else {
                    barangAdapter.notifyDataSetChanged()
                }
                val waktu = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                val aksi = if (isPengembalian) "Kembali" else "Pinjam"
                val riwayat = Riwayat(
                    namaUser = namaUser ?: "Guest",
                    nimUser = nimUser ?: "-",
                    namaBarang = barang.nama,
                    aksi = aksi,
                    waktu = waktu
                )
                DataGlobal.daftarRiwayat.add(0, riwayat)
                PrefRiwayat.simpan(this, DataGlobal.daftarRiwayat)
            } else {
                Toast.makeText(this, "Barang tidak ditemukan!\n(QR: $qrData)", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Scan dibatalkan", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val REQUEST_PINJAM = 100
        const val REQUEST_KEMBALIKAN = 101
    }
}

