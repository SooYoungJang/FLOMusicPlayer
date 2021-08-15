package com.example.flomusicplayer.presentation.view.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flomusicplayer.presentation.view.MainActivity
import com.example.flomusicplayer.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private var isBackPressed = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startMainActivity(this)
    }

    override fun onBackPressed() {
        isBackPressed = true
        finish()
        super.onBackPressed()
    }

    fun startMainActivity(context: Context) = runBlocking<Unit> {
        GlobalScope.launch {
            delay(2000)
            if(!isBackPressed) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}
