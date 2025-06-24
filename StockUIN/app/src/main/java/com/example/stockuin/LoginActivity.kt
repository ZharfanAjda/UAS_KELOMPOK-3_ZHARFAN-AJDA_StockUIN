package com.example.stockuin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class LoginActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etNama = findViewById(R.id.etNama)
        etNim = findViewById(R.id.etNim)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()

            if (nama.isEmpty() || nim.isEmpty()) {
                Toast.makeText(this, "Nama dan NIM tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan info user (kalau mau pakai SharedPreferences)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("NAMA_USER", nama)
            intent.putExtra("NIM_USER", nim)
            startActivity(intent)
        }
    }
}
