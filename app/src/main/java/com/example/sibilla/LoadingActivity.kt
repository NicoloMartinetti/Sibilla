package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class LoadingActivity : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var dot1: ImageView
    private lateinit var dot2: ImageView
    private lateinit var dot3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        logo = findViewById(R.id.logo)
        dot1 = findViewById(R.id.dot1)
        dot2 = findViewById(R.id.dot2)
        dot3 = findViewById(R.id.dot3)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                logo.rotation = logo.rotation + 10f
                when (dot1.alpha) {
                    1.0f -> dot1.alpha = 0.9f
                    0.9f -> dot1.alpha = 0.8f
                    0.8f -> dot1.alpha = 0.7f
                    0.7f -> dot1.alpha = 0.6f
                    0.6f -> dot1.alpha = 0.5f
                    0.5f -> dot1.alpha = 0.4f
                    0.4f -> dot1.alpha = 0.3f
                    0.3f -> dot1.alpha = 0.2f
                    0.2f -> dot1.alpha = 0.1f
                    0.1f -> dot1.alpha = 0.0f
                    0.0f -> dot1.alpha = 1.0f
                }
                when (dot2.alpha) {
                    1.0f -> dot2.alpha = 0.9f
                    0.9f -> dot2.alpha = 0.8f
                    0.8f -> dot2.alpha = 0.7f
                    0.7f -> dot2.alpha = 0.6f
                    0.6f -> dot2.alpha = 0.5f
                    0.5f -> dot2.alpha = 0.4f
                    0.4f -> dot2.alpha = 0.3f
                    0.3f -> dot2.alpha = 0.2f
                    0.2f -> dot2.alpha = 0.1f
                    0.1f -> dot2.alpha = 0.0f
                    0.0f -> dot2.alpha = 1.0f
                }
                when (dot3.alpha) {
                    1.0f -> dot3.alpha = 0.9f
                    0.9f -> dot3.alpha = 0.8f
                    0.8f -> dot3.alpha = 0.7f
                    0.7f -> dot3.alpha = 0.6f
                    0.6f -> dot3.alpha = 0.5f
                    0.5f -> dot3.alpha = 0.4f
                    0.4f -> dot3.alpha = 0.3f
                    0.3f -> dot3.alpha = 0.2f
                    0.2f -> dot3.alpha = 0.1f
                    0.1f -> dot3.alpha = 0.0f
                    0.0f -> dot3.alpha = 1.0f
                }
            }
        }, 0, 100)

        Handler().postDelayed({
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}