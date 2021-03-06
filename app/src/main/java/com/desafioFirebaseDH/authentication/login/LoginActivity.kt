package com.desafioFirebaseDH.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.view.home.HomeActivity
import com.desafioFirebaseDH.authentication.register.RegisterActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private val _user = auth?.currentUser
    private lateinit var email: EditText
    private lateinit var senha: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCriarConta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        email = findViewById(R.id.edtEmailLogin)
        senha = findViewById(R.id.edtPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        btnCriarConta = findViewById(R.id.btnCreateAccount)

        btnCriarConta.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (validaCamposLogin()) {
                signInFirebase(email.text.toString(), senha.text.toString())
            }
        }
    }

    private fun signInFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("FIREBASE", "signInWithEmail:success")
                    val user = auth.currentUser
                    intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)

                } else {
                    Log.w("FIREBASE", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validaCamposLogin(): Boolean {
        var resultado = true

        if (email.text?.isBlank()!!) {
            findViewById<TextInputLayout>(R.id.email_text_input).editText?.error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        if (senha.text?.isBlank()!!) {
            findViewById<TextInputLayout>(R.id.password_text_input).editText?.error =
                getString(R.string.campo_vazio)
            resultado = false
        }
        return resultado
    }
}