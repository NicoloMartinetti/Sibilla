package com.example.sibilla

import android.content.Intent
import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var next: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var finalString : String? = intent.getStringExtra("finalString")

        result = findViewById(R.id.result)
        next = findViewById(R.id.next)

        result.text = finalString.toString()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        next.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}