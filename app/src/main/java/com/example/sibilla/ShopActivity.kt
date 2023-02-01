package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class ShopActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageButton
    private lateinit var faq: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        backArrow = findViewById(R.id.backArrow)
        faq = findViewById(R.id.faq)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }

        faq.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}