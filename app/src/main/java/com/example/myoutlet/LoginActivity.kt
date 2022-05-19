package com.example.myoutlet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myoutlet.Klarna.WebViewKlarna
import com.example.myoutlet.databinding.PgSinginBinding
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import okhttp3.Response
import java.io.IOException

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
      startSDK("https://www.klarna.com/demo/")
    }
  }

  private fun startSDK(url: String?) {
    Intent(this, WebViewKlarna::class.java)
      .apply {
        putExtra(WebViewKlarna.KEY_URL, url)
        startActivity(this)
      }
  }

}
