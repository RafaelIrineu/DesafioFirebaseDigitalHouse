package com.desafioFirebaseDH.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.desafioFirebaseDH.MainActivity
import com.desafioFirebaseDH.R
import com.desafioFirebaseDH.view.home.HomeActivity
import com.desafioFirebaseDH.view.login.LoginActivity
import com.desafioFirebaseDH.view.register.RegisterActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, HANDLER_TIME)
    }

    companion object{
        const val HANDLER_TIME: Long = 4000
    }
}