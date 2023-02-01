package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        Handler().postDelayed({
            val intent = Intent(this, Tutorial1Activity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}