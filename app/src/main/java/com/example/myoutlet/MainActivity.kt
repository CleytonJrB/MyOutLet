package com.example.myoutlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myoutlet.databinding.PgMainBinding
import com.example.myoutlet.klarna.KlarnaSingleton
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView

class MainActivity : AppCompatActivity() {

  private var _binding: PgMainBinding? = null
  private val binding get() = _binding!!

  private val paymentViewKlarna by lazy { findViewById<KlarnaPaymentView>(R.id.paymentKlarna_View) }


  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)

    _binding = PgMainBinding.inflate(layoutInflater)

    setContentView(binding.root)

    KlarnaSingleton.initialize(this,paymentViewKlarna)

    Log.d("wwwd", "onCreate View MainActivity")

  }
}