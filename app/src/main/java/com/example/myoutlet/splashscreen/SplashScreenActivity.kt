package com.example.myoutlet.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.myoutlet.LoginActivity
import com.example.myoutlet.MainActivity
import com.example.myoutlet.R

class SplashScreenActivity : AppCompatActivity() {

  lateinit var handler: Handler
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash_screen)

    getWindow().setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN)
    handler=Handler()
    handler.postDelayed({
      val intent = Intent(this, LoginActivity::class.java)
      startActivity(intent)
      finish()

    },3000)  //delaying 3 seconds

  }
}