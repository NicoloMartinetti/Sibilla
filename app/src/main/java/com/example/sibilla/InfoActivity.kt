package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class InfoActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        backArrow = findViewById(R.id.backArrow)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}