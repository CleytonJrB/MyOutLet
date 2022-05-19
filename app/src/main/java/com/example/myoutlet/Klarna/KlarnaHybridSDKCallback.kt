package com.example.myoutlet.Klarna

import android.webkit.WebView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.klarna.mobile.sdk.KlarnaMobileSDKError
import com.klarna.mobile.sdk.api.OnCompletion
import com.klarna.mobile.sdk.api.hybrid.KlarnaHybridSDKCallback

class KlarnaHybridSDKCallback(private val rootlayout:ConstraintLayout) : KlarnaHybridSDKCallback {
  override fun didHideFullscreenContent(webView: WebView, completion: OnCompletion) {
    TODO("Not yet implemented")
    completion.run()
  }

  override fun didShowFullscreenContent(webView: WebView, completion: OnCompletion) {
    TODO("Not yet implemented")
    completion.run()
  }

  override fun onErrorOccurred(webView: WebView, error: KlarnaMobileSDKError) {
    TODO("Not yet implemented")
    Toast.makeText(webView.context, "An error occurred: $error", Toast.LENGTH_LONG).show()
  }

  override fun willHideFullscreenContent(webView: WebView, completion: OnCompletion) {
    TODO("Not yet implemented")
    completion.run()
  }

  override fun willShowFullscreenContent(webView: WebView, completion: OnCompletion) {
    TODO("Not yet implemented")
    completion.run()

  }
}