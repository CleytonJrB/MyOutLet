package com.example.myoutlet.klarna.klarnaReponse

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myoutlet.databinding.PaymentDeclinedBinding
import com.example.myoutlet.databinding.PaymentSuccessBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentDeclined : BottomSheetDialogFragment() {
  private var _binding: PaymentDeclinedBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = PaymentDeclinedBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    requireView().layoutParams.height =
      (Resources.getSystem().displayMetrics.heightPixels * 0.95).toInt()
    requireView().requestLayout()

    GlobalScope.launch(Dispatchers.Main) {
      delay(2000)
      this@PaymentDeclined.dismiss()
    }
  }
}