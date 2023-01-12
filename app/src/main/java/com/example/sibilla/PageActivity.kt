package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class PageActivity : AppCompatActivity() {

    private lateinit var question: EditText
    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var hometown: EditText
    private lateinit var sender: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        question = findViewById(R.id.answer)
        name = findViewById(R.id.firstName)
        surname = findViewById(R.id.lastName)
        hometown = findViewById(R.id.bornPlace)
        sender = findViewById(R.id.sender)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        sender.setOnClickListener {
            if (question.text.isNotEmpty() &&
                name.text.isNotEmpty() &&
                surname.text.isNotEmpty() &&
                hometown.text.isNotEmpty()
            ) {
                val intent = Intent(this, LoadingActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Check empty fields", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
        }
    }
}