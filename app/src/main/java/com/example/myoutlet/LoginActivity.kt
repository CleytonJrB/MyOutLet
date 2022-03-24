package com.example.myoutlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myoutlet.databinding.PgMapBinding
import com.example.myoutlet.databinding.PgSinginBinding

private lateinit var binding: PgSinginBinding

class LoginActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PgSinginBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.btnSingin.setOnClickListener {
      val intent = Intent( this,MainActivity::class.java )
      startActivity(intent)
      finish()
    }

  }
}