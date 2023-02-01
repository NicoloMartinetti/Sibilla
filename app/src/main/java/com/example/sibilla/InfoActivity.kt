package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class InfoActivity : AppCompatActivity() {

    private lateinit var backArrow: ImageButton
    private lateinit var shop: LinearLayout
    private lateinit var firstCazzillo: ImageButton
    private lateinit var secondCazzillo: ImageButton
    private lateinit var account: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        backArrow = findViewById(R.id.backArrow)
        shop = findViewById(R.id.shop)
        firstCazzillo = findViewById(R.id.firstCazzillo)
        secondCazzillo = findViewById(R.id.secondCazzillo)
        account = findViewById(R.id.account)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }

        shop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            finish()
        }

        firstCazzillo.setOnClickListener {
            val intent = Intent(this, InfoDetail1Activity::class.java)
            startActivity(intent)
            finish()
        }

        secondCazzillo.setOnClickListener {
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