package com.example.myoutlet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myoutlet.databinding.PgSinginBinding

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: PgSinginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PgSinginBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    setOnClick()

    binding.btnSingin.setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
    }
  }

  private fun setOnClick() {
    binding.btnKlarna.setOnClickListener {
      val intent = Intent(this,SDKKlarna::class.java)
      startActivity(intent)
    }
  }

}
