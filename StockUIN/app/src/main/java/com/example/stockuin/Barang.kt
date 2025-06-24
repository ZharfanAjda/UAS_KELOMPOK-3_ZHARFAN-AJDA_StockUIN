package com.example.stockuin

data class Barang(
    val nama: String,
    val deskripsi: String,
    val gambar: Int,
    var jumlah: Int,
    val kodeQR: String
)