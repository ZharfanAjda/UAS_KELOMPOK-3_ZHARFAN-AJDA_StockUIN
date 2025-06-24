package com.example.stockuin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockuin.R
import com.example.stockuin.Barang

class BarangAdapter(private val listBarang: MutableList<Barang>) :
    RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    inner class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang: TextView = itemView.findViewById(R.id.namaBarang)
        val deskripsiBarang: TextView = itemView.findViewById(R.id.deskripsiBarang)
        val imgBarang: ImageView = itemView.findViewById(R.id.imgBarang)
        val tvjumlah: TextView = itemView.findViewById(R.id.tvJumlah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_barang, parent, false)
        return BarangViewHolder(view)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barang = listBarang[position]
        holder.namaBarang.text = barang.nama
        holder.deskripsiBarang.text = barang.deskripsi
        holder.imgBarang.setImageResource(barang.gambar)
        holder.tvjumlah.text = "Jumlah: ${barang.jumlah}"
    }

    override fun getItemCount(): Int = listBarang.size
}
