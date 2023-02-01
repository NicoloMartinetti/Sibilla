package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class InfoDetail2Activity : AppCompatActivity() {

    private lateinit var backArrow: ImageButton
    private lateinit var faq: ImageButton
    private lateinit var shop: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail_2)

        backArrow = findViewById(R.id.backArrow)
        faq = findViewById(R.id.faq)
        shop = findViewById(R.id.shop)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }

        faq.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }

        shop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}