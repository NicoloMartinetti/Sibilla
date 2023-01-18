package com.example.sibilla

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var finalString : String? = intent.getStringExtra("finalString")

        result = findViewById(R.id.result)

        result.text = finalString.toString()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}