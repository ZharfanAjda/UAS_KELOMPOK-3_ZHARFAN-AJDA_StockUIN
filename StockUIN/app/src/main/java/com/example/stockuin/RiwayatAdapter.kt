package com.example.stockuin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(private val list: List<Riwayat>) :
    RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaBarang: TextView = view.findViewById(R.id.tvNamaBarang)
        val tvAksi: TextView = view.findViewById(R.id.tvAksi)
        val tvUser: TextView = view.findViewById(R.id.tvUser)
        val tvWaktu: TextView = view.findViewById(R.id.tvWaktu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val data = list[position]
        holder.tvNamaBarang.text = data.namaBarang
        holder.tvAksi.text = "Aksi: ${data.aksi}"
        holder.tvUser.text = "${data.namaUser} (${data.nimUser})"
        holder.tvWaktu.text = data.waktu
    }

    override fun getItemCount(): Int = list.size
}
