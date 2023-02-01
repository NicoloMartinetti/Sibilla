package com.example.sibilla

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var login: TextView
    private lateinit var db: FirebaseFirestore
    private lateinit var signIn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var nome: EditText
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var pwd: EditText
    private lateinit var skip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        login = findViewById(R.id.login)
        nome=findViewById(R.id.nome)
        email=findViewById(R.id.email)
        username=findViewById(R.id.username)
        pwd=findViewById(R.id.password)
        signIn = findViewById(R.id.send)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        skip = findViewById(R.id.skip)

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

        signIn.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener {
                    val currentUser=auth.currentUser
                    val userHashMap = hashMapOf(
                        "nome" to nome.text.toString(),
                        "username" to username.text.toString()
                    )

                    if (currentUser != null) {
                        db.collection("users").document(currentUser.uid).set(userHashMap)
                            .addOnCompleteListener {
                                editor.putString("Logged", "Yes")
                                editor.apply()
                                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                    }
                }

            auth.signInWithEmailAndPassword("email", "password")
                .addOnCompleteListener {

                }
                .addOnFailureListener {

                }
        }

        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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
}