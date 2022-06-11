package io.usmon.trafficlaws.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.usmon.trafficlaws.R
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            Intent(this@SplashActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
                cancel()
            }
        }
    }
}