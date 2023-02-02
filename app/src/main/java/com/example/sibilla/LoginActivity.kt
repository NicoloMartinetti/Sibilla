package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
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

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var google: ImageButton

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

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)
        google = findViewById(R.id.google)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            navigateToSecondActivity()
        }

        google.setOnClickListener(View.OnClickListener { signIn() })

        val preferences = getSharedPreferences("Login", MODE_PRIVATE)
        val loginRegistration = preferences.getString("Logged", "")
        val editor = preferences.edit()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        if (loginRegistration.equals("No")) {
            skip.isEnabled = false
            skip.visibility = View.GONE
        }

        login.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(),pwd.text.toString())
                .addOnSuccessListener {
                    editor.putString("Logged", "Yes")
                    editor.apply()
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

    fun signIn() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                navigateToSecondActivity()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun navigateToSecondActivity() {
        val intent = Intent(this, PageActivity::class.java)
        startActivity(intent)
        finish()
    }
}