package com.example.stockuin

object DataGlobal {
    var daftarBarang = mutableListOf<Barang>(
        Barang("Bendera Kegiatan", "Bendera organisasi untuk kegiatan formal", R.drawable.bendera, 2, "Bendera"),
        Barang("Mic", "Mikrofon untuk acara atau rapat", R.drawable.mic, 5, "Mic"),
        Barang("Sound System", "Speaker besar untuk output suara", R.drawable.soundsys, 3, "Sound"),
        Barang("Buku Tulis","Buku untuk keperluan menulis materi dari dosen atau notulensi", R.drawable.buku, 10,"Buku"),
        Barang("Pulpen", "Alat tulis pulpen", R.drawable.pulpen, 11,"Pulpen"),
        Barang("Kabel HDMI", "Untuk menghubungkan perangkat laptop ke perangkat TV", R.drawable.hdmi, 2,"HDMI")
    )
    var daftarRiwayat = mutableListOf<Riwayat>()
    val daftarInventory = mutableListOf(
        InventoryBarang("Bendera Kegiatan", 0, "Bagus Semua"),
        InventoryBarang("Mic", 1, "Rusak"),
        InventoryBarang("Sound System", 0, "Bagus"),
        InventoryBarang("Buku Tulis", 0, "Bagus"),
        InventoryBarang("Pulpen", 2, "Habis"),
        InventoryBarang("Kabel HDMI", 0, "Bagus")
    )
}
