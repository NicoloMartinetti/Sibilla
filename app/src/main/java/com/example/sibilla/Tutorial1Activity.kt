package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Tutorial1Activity : AppCompatActivity() {

    private lateinit var skip: TextView
    private lateinit var avanti: Button
    private lateinit var auth: FirebaseAuth

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_1)

        skip = findViewById(R.id.skip)
        avanti = findViewById(R.id.avanti)
        auth = Firebase.auth

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso);

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        avanti.setOnClickListener {
            val intent = Intent(this, Tutorial2Activity::class.java)
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

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if(acct != null)
        {
            val intent = Intent(this, PageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}