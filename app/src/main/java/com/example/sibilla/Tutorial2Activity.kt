package com.example.sibilla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


class Tutorial2Activity : AppCompatActivity() {

    private lateinit var skip: TextView
    private lateinit var avanti: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_2)

        skip = findViewById(R.id.skip)
        avanti = findViewById(R.id.avanti)
        auth = Firebase.auth

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        avanti.setOnClickListener {
            val intent = Intent(this, Tutorial3Activity::class.java)
            startActivity(intent)
            finish()
        }

        skip.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onStart() {
        super.onStart()
        val currentUser=auth.currentUser
        if(currentUser != null)
        {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}