package com.desafioFirebaseDH.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.desafioFirebaseDH.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var senha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        email = findViewById(R.id.edtEmailLogin)
        senha = findViewById(R.id.edtPasswordLogin)

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            signInFirebase(email.text.toString(), senha.text.toString())
        }
    }

    private fun signInFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { Log.d("FIREBASE", "signInWithEmail:success")
                    val user = auth.currentUser

                } else {
                    Log.w("FIREBASE", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}