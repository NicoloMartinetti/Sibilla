package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileActivity: AppCompatActivity() {

    private lateinit var backArrow: ImageButton
    private lateinit var shop: LinearLayout
    private lateinit var info: ImageButton
    private lateinit var name: TextView
    private lateinit var exit: TextView
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        backArrow = findViewById(R.id.backArrow)
        shop = findViewById(R.id.shop)
        info = findViewById(R.id.faq)
        name = findViewById(R.id.name)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        exit = findViewById(R.id.exit)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso);

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

        info.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {
            auth.signOut()
            gsc.signOut().addOnCompleteListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        var currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    name.text = it.result.data?.get("username").toString()
                }
            }
        }
    }
}