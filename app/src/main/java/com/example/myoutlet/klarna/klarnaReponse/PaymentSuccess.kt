package com.example.myoutlet.klarna.klarnaReponse

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.R
import com.example.myoutlet.databinding.PaymentSuccessBinding
import com.example.myoutlet.model.ModalState
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentSuccess : BottomSheetDialogFragment() {

  private var _binding: PaymentSuccessBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = PaymentSuccessBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    requireView().layoutParams.height =
      (Resources.getSystem().displayMetrics.heightPixels * 0.95).toInt()
    requireView().requestLayout()

    GlobalScope.launch(Dispatchers.Main) {
      delay(2000)
      this@PaymentSuccess.dismiss()
    }
  }
}