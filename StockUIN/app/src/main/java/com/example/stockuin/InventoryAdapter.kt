package com.example.stockuin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InventoryAdapter(private val list: List<InventoryBarang>) :
    RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    class InventoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaBarang: TextView = view.findViewById(R.id.tvNamaBarang)
        val tvJumlah: TextView = view.findViewById(R.id.tvJumlah)
        val tvKondisi: TextView = view.findViewById(R.id.tvKondisi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_inventory, parent, false)
        return InventoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = list[position]
        holder.tvNamaBarang.text = item.nama
        holder.tvJumlah.text = "Jumlah: ${item.jumlah}"
        holder.tvKondisi.text = "Kondisi: ${item.kondisi}"
    }

    override fun getItemCount(): Int = list.size
}
