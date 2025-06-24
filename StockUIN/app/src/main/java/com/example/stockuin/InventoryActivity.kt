package com.example.stockuin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val rvInventory = findViewById<RecyclerView>(R.id.rvInventory)
        rvInventory.layoutManager = LinearLayoutManager(this)
        rvInventory.adapter = InventoryAdapter(DataGlobal.daftarInventory)
    }
}
