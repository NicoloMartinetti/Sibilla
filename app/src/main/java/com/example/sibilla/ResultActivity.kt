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
    private lateinit var account: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var finalString : String? = intent.getStringExtra("finalString")

        result = findViewById(R.id.result)
        next = findViewById(R.id.next)
        faq = findViewById(R.id.faq)
        shop = findViewById(R.id.shop)
        account = findViewById(R.id.account)

        result.text = finalString.toString()

        val preferences = getSharedPreferences("Login", MODE_PRIVATE)
        val loginRegistration = preferences.getString("Logged", "")
        val editor = preferences.edit()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        next.setOnClickListener {
            if (loginRegistration.equals("Yes")) {
                val intent = Intent(this, PageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                editor.putString("Logged","No")
                editor.apply()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
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

        account.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}