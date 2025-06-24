package com.example.stockuin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvNama = findViewById<TextView>(R.id.tvNamaProfile)
        val tvNim = findViewById<TextView>(R.id.tvNimProfile)

        val nama = intent.getStringExtra("NAMA_USER") ?: "Tidak diketahui"
        val nim = intent.getStringExtra("NIM_USER") ?: "Tidak diketahui"

        tvNama.text = "Nama: $nama"
        tvNim.text = "NIM: $nim"
    }
}
