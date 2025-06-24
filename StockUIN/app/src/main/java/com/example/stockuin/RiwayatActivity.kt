package com.example.stockuin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RiwayatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        val rvRiwayat = findViewById<RecyclerView>(R.id.rvRiwayat)
        rvRiwayat.layoutManager = LinearLayoutManager(this)
        rvRiwayat.adapter = RiwayatAdapter(DataGlobal.daftarRiwayat)
    }
}
