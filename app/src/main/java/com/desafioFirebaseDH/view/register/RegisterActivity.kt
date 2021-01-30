package com.desafioFirebaseDH.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var nome: EditText
    private lateinit var email: EditText
    private lateinit var senha: EditText
    private lateinit var repetirSenha: EditText
    private lateinit var btnCriarConta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        nome = findViewById(R.id.edtNameRegister)
        email = findViewById(R.id.edtEmailRegister)
        senha = findViewById(R.id.edtPasswordRegister)
        repetirSenha = findViewById(R.id.edtPasswordRepeatRegister)
        btnCriarConta = findViewById(R.id.btnCreateAccountRegister)

        btnCriarConta.setOnClickListener {
            signUpFirebase(email.text.toString(), senha.text.toString())
        }
    }

    private fun signUpFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("FIREBASE", "createUserWithEmail:success")
                    val user = auth.currentUser
                    intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "SignIn Error", Toast.LENGTH_LONG).show()
                    Log.w("FIREBASE", "createUserWithEmail:failure",
                        task.exception)
                    Toast.makeText(
                        this@RegisterActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}