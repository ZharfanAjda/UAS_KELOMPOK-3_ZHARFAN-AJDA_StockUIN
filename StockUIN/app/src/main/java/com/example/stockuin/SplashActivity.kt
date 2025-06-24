package com.example.stockuin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private fun buatAnimasiJatuh(dariAtas: Float): TranslateAnimation {
        val anim = TranslateAnimation(0f, 0f, dariAtas, 0f)
        anim.duration = 2000
        anim.fillAfter = true
        return anim
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logoImage)
        val text = findViewById<TextView>(R.id.textAppName)

        val bulat1 = findViewById<ImageView>(R.id.bulat1)
        val bulat2 = findViewById<ImageView>(R.id.bulat2)
        val bulat3 = findViewById<ImageView>(R.id.bulat3)
        val bulat4 = findViewById<ImageView>(R.id.bulat4)

        logo.startAnimation(buatAnimasiJatuh(-600f))
        bulat1.startAnimation(buatAnimasiJatuh(-800f))
        bulat2.startAnimation(buatAnimasiJatuh(-700f))
        bulat3.startAnimation(buatAnimasiJatuh(-650f))
        bulat4.startAnimation(buatAnimasiJatuh(-750f))

        Handler(Looper.getMainLooper()).postDelayed({
            text.visibility = View.VISIBLE
        }, 2000)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
