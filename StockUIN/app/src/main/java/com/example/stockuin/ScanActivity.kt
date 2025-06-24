package com.example.stockuin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stockuin.databinding.ActivityScanBinding
import com.google.zxing.integration.android.IntentIntegrator

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mulai scan saat activity dibuka
        startQRScanner()
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Scan QR Barang")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val hasilQR = result.contents.trim() // Hasil dari QR Scanner

                // Cek apakah kode QR cocok dengan salah satu barang
                val barangDipilih = DataGlobal.daftarBarang.find { it.kodeQR == hasilQR }

                if (barangDipilih != null) {
                    if (barangDipilih.jumlah > 0) {
                        barangDipilih.jumlah -= 1
                        Toast.makeText(
                            this,
                            "${barangDipilih.nama} dipinjam, sisa ${barangDipilih.jumlah}",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Kirim sinyal ke MainActivity
                        val returnIntent = Intent()
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Stok ${barangDipilih.nama} habis!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }
}