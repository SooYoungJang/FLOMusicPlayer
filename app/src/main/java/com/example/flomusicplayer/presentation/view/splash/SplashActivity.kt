package com.example.flomusicplayer.presentation.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flomusicplayer.R
import com.example.flomusicplayer.presentation.view.MainActivity
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
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            finish()
        }
    }

}
