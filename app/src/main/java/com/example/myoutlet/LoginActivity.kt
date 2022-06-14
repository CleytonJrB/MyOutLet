package com.example.myoutlet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myoutlet.databinding.PgSinginBinding
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentActions
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: PgSinginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PgSinginBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.btnSingin.setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
      finish()
    }
  }
}

