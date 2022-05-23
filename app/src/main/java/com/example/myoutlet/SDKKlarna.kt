package com.example.myoutlet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.myoutlet.klarna.OrderClient
import com.example.myoutlet.klarna.OrderPayload
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentCategory
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentViewCallback
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentsSDKError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class SDKKlarna : AppCompatActivity(), KlarnaPaymentViewCallback {

  private val paymentViewKlarna by lazy { findViewById<KlarnaPaymentView>(R.id.paymentView_klarna) }
  private val btnPayWithKlarna by lazy { findViewById<Button>(R.id.btn_payWith_klarna) }

  private val paymentCategory = KlarnaPaymentCategory.PAY_NOW

  private var job: Job? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.klarna_view)

    initialize()
    setupButton()
    paymentViewKlarna.category = paymentCategory

  }

  @SuppressLint("MissingPermission")
  private fun initialize() {
    if (OrderClient.hasSetCredentials()) {
      job = GlobalScope.launch {

        val sessionCall = OrderClient.instance.createCreditSession(OrderPayload.defaltPayload)
        try {
          val resp = sessionCall.execute()
          resp.body()?.let { session ->
            runOnUiThread {
              paymentViewKlarna.initialize(
                session.client_token,
                "${getString(R.string.return_url_scheme)}://${getString(R.string.return_url_host)}"
              )
              Log.d("LOGKlarna", "Initialize - Successful")
            }
          }
        } catch (exception: Exception) {
          showError(exception.message)
        }
      }
    } else {
      showError("Please enter your credentials first. In OrderClient")
    }
  }

  private fun runOnUiThread(action: () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
      action.invoke()
    }
  }

  private fun setupButton(){
    btnPayWithKlarna.setOnClickListener{

      paymentViewKlarna.authorize(true,null)
    }
  }

  override fun onAuthorized(
    view: KlarnaPaymentView,
    approved: Boolean,
    authToken: String?,
    finalizedRequired: Boolean?
  ) {
  }

  override fun onErrorOccurred(view: KlarnaPaymentView, error: KlarnaPaymentsSDKError) {
    println("An error ocurrend: ${error.name} - ${error.message}")
    if (error.isFatal) {
      paymentViewKlarna.visibility = View.INVISIBLE
    }
  }

  private fun showError(message: String?) {
    runOnUiThread {
      val alertDialog = AlertDialog.Builder(this).create()
      alertDialog.setMessage(message ?: "Something went wrong. Please try again later.")
      alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, _ ->
        dialog.dismiss()
      }
      alertDialog.show()
    }
  }

  override fun onFinalized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {

  }

  override fun onInitialized(view: KlarnaPaymentView) {
    view.load(null)
  }

  override fun onLoadPaymentReview(view: KlarnaPaymentView, showForm: Boolean) {}

  override fun onLoaded(view: KlarnaPaymentView) {
    Log.d("LOGKlarna", "onLoaded - Successful")
  }

  override fun onReauthorized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {}

  override fun onResume() {
    super.onResume()
    paymentViewKlarna.registerPaymentViewCallback(this)
  }
}


