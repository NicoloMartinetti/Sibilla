package com.example.sibilla

import android.content.Intent
import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var next: TextView
    private lateinit var faq: ImageButton
    private lateinit var shop: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var finalString : String? = intent.getStringExtra("finalString")

        result = findViewById(R.id.result)
        next = findViewById(R.id.next)
        faq = findViewById(R.id.faq)
        shop = findViewById(R.id.shop)

        result.text = finalString.toString()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        next.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
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