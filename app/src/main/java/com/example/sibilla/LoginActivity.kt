package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var register: TextView
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var login: Button
    private lateinit var email: EditText
    private lateinit var pwd: EditText
    private lateinit var skip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register = findViewById(R.id.register)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        email = findViewById(R.id.email)
        pwd = findViewById(R.id.password)
        login = findViewById(R.id.send)
        skip = findViewById(R.id.skip)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        login.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(),pwd.text.toString())
                .addOnSuccessListener {
                    val intent = Intent(this, PageActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Email o Password errate", Toast.LENGTH_SHORT).show()
                }
        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        skip.setOnClickListener {
            val intent = Intent(this, PageActivity::class.java)
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