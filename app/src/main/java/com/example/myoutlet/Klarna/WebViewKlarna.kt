package com.example.myoutlet.Klarna

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myoutlet.R
import com.klarna.mobile.sdk.api.hybrid.KlarnaHybridSDK
import kotlinx.android.synthetic.main.webview_main.*


class WebViewKlarna : AppCompatActivity() {

  companion object {
    const val KEY_URL: String = "URL"
  }


  private lateinit var klarnaHybridSDK: KlarnaHybridSDK


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.webview_main)

    setupSDK()

    intent.getStringExtra(KEY_URL)?.let {
      loadUrl(it)
    }

  }

    @SuppressLint("MissingPermission")
  private fun setupSDK() {

    klarnaHybridSDK = KlarnaHybridSDK(
      "${getString(R.string.return_url_scheme)}://${getString(R.string.return_url_host)}",
      KlarnaHybridSDKCallback(findViewById(R.id.webViewMain))
    )
    webViewMain.webViewClient = MyWebViewClient()
    webViewMain.webChromeClient = WebChromeClient()
    webViewMain.settings.javaScriptEnabled = true
    webViewMain.settings.domStorageEnabled = true
    klarnaHybridSDK.addWebView(webViewMain)

  }

  private fun loadUrl(url: String) {

    var address = url
    if (!address.contains("://")) {
      address = "https://$address"
    }
    webViewMain.loadUrl(address)

  }

  inner class MyWebViewClient() : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
      return !klarnaHybridSDK.shouldFollowNavigation(url)
    }

    override fun onPageFinished(view: WebView, url: String) {
      klarnaHybridSDK.newPageLoad(view)
    }
  }

  override fun onBackPressed() {
    if (webViewMain.canGoBack()) {
      webViewMain.goBack()
      return
    }
    super.onBackPressed()
  }

  override fun onDestroy() {
    super.onDestroy()
    webViewMain.destroy()
  }
}