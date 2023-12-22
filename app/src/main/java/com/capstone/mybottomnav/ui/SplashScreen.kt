package com.capstone.mybottomnav.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.mybottomnav.MainActivity
import com.capstone.mybottomnav.R
import com.capstone.mybottomnav.data.UserPreferences

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        moveNext()



    }
    private fun moveNext() {
        //  Handler().postDelayed({
        val userPref = UserPreferences(this)
        val intent: Intent

        if (userPref.getUser().token.toString().isNotEmpty()) {
            intent = Intent(this@SplashScreen, MainActivity::class.java)
        } else {
            intent = Intent(this@SplashScreen, OnBoardingActivity::class.java)
        }

        startActivity(intent)
        finish()

        //  }, SPLASH_SCREEN_LENGTH)
    }
}