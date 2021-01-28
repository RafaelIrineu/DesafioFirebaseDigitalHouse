package com.desafioFirebaseDH.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.desafioFirebaseDH.MainActivity
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.login.LoginActivity
import com.desafioFirebaseDH.register.RegisterActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }, HANDLER_TIME)
    }

    companion object{
        const val HANDLER_TIME: Long = 4000
    }
}