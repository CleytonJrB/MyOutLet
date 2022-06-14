package com.example.myoutlet.klarna

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.R
import com.example.myoutlet.interfaces.OrderResponseCallBack
import com.example.myoutlet.model.ModalState
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentCategory
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentViewCallback
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentsSDKError
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

internal object KlarnaSingleton : KlarnaPaymentViewCallback {

  private lateinit var paymentViewKlarna: KlarnaPaymentView
  private const val paymentCategory = KlarnaPaymentCategory.PAY_LATER
  private val myOutletBridge = MyOutLetBridge
  private lateinit var klarnaCallBack: OrderResponseCallBack

  @SuppressLint("MissingPermission")
  fun initialize(activity: AppCompatActivity, payView: KlarnaPaymentView) {


    paymentViewKlarna = payView
    paymentViewKlarna.registerPaymentViewCallback(this)
    paymentViewKlarna.category = paymentCategory

    GlobalScope.launch {
      val sessionCall = OrderClient.instance.createCreditSession(OrderPayload.defaltPayload)
      try {
        val resp = sessionCall.execute()
        resp.body()?.let { session ->
          activity.runOnUiThread {
            paymentViewKlarna.initialize(
              session.client_token,
              "${activity.getString(R.string.return_url_host)}://${activity.getString(R.string.return_url_scheme)}"
            )
            Log.d("LOGKlarna", "Run Initialize")
          }
        }
      } catch (exception: Exception) {
        (exception.message)
      }
    }
  }

  fun showPaymentView(callBack: OrderResponseCallBack) {
    paymentViewKlarna.authorize(false, null)
    klarnaCallBack = callBack
  }

  override fun onAuthorized(
    view: KlarnaPaymentView,
    approved: Boolean,
    authToken: String?,
    finalizedRequired: Boolean?,
  ) {
    if (!approved) {
      Log.d("LOGKlarna", "Payment not approved")
      klarnaCallBack.onAuthorizationError()
//      myOutletBridge.viewModel?.modalState?.postValue(ModalState.ON_ERROR)
      Toast.makeText(
        paymentViewKlarna.context,
        "Payment not approved",
        Toast.LENGTH_LONG
      ).show()
      return
    }
    Log.d("LOGKlarna", "Payment approved")
//    myOutletBridge.viewModel?.modalState?.postValue(ModalState.ON_SUCCESS)
    klarnaCallBack.onAuthorizationSuccess()
    Toast.makeText(view.context, "Payment approved", Toast.LENGTH_LONG).show()
  }

  override fun onErrorOccurred(view: KlarnaPaymentView, error: KlarnaPaymentsSDKError) {
    Log.d("LOGKlarna", "Payment error")
  }

  override fun onFinalized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {}

  override fun onInitialized(view: KlarnaPaymentView) {
    view.load(null)
  }

  override fun onLoadPaymentReview(view: KlarnaPaymentView, showForm: Boolean) {}

  override fun onLoaded(view: KlarnaPaymentView) {
    Log.d("LOGKlarna", "Payment loaded")
  }

  override fun onReauthorized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {}
}
