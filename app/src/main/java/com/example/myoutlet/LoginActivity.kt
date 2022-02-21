package com.example.myoutlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_singin)

    val onClick = findViewById<Button>(R.id.btn_singin)

    onClick.setOnClickListener {
      val intent = Intent( this,MainActivity::class.java )
      startActivity(intent)
      finish()
    }

  }
}