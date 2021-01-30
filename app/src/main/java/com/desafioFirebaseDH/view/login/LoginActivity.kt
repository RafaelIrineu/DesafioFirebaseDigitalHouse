package com.desafioFirebaseDH.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.desafioFirebaseDH.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val emailContainer = findViewById<TextInputEditText>(R.id.edtEmailLogin).toString()
            val passwordContainer = findViewById<TextInputEditText>(R.id.edtPasswordLogin).toString()

//            signInFirebase(emailContainer, passwordContainer)
        }

    }

    }

//    private fun signInFirebase(email: String, password: String) {
//        auth!!.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(
//                this
//            ) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("FIREBASE", "signInWithEmail:success")
//                    val user = auth!!.currentUser
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(
//                        "FIREBASE",
//                        "signInWithEmail:failure",
//                        task.exception
//                    )
//                    Toast.makeText(
//                        this@LoginActivity, "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                }
//
//            }
//    }
//
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            reload();
//        }
//    }
//}
