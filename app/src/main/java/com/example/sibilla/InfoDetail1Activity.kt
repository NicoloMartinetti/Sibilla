package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class InfoDetail1Activity : AppCompatActivity() {

    private lateinit var backArrow: ImageButton
    private lateinit var faq: ImageButton
    private lateinit var shop: LinearLayout
    private lateinit var avanti: Button
    private lateinit var account: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail_1)

        backArrow = findViewById(R.id.backArrow)
        faq = findViewById(R.id.faq)
        shop = findViewById(R.id.shop)
        avanti = findViewById(R.id.avanti)
        account = findViewById(R.id.account)

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

        avanti.setOnClickListener {
            val intent = Intent(this, InfoDetail2Activity::class.java)
            startActivity(intent)
            finish()
        }

        account.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}