package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.R
import com.example.myoutlet.databinding.PgProductBinding
import com.example.myoutlet.interfaces.OrderResponseCallBack
import com.example.myoutlet.klarna.KlarnaSingleton
import com.example.myoutlet.klarna.klarnaReponse.PaymentDeclined
import com.example.myoutlet.klarna.klarnaReponse.PaymentSuccess
import com.squareup.picasso.Picasso

internal class ProductFragment : Fragment() {

  private var _binding: PgProductBinding? = null
  private val binding get() = _binding!!

  private val viewModel = MyOutLetBridge.viewModel

  companion object {
    fun newInstance() = ProductFragment()
  }

  private val args: ProductFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = PgProductBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val paymentSuccessModel = PaymentSuccess()
    val paymentDeclinedModel = PaymentDeclined()

    binding.btnSearch.setOnClickListener {

      findNavController().navigate(R.id.fromProductFragmenttoMapFragment)
    }
    binding.btnKlarnaP.setOnClickListener {
      KlarnaSingleton.showPaymentView(object : OrderResponseCallBack {
        override fun onAuthorizationSuccess() {
          paymentSuccessModel.show(childFragmentManager, "paymentSuccessModel")
        }

        override fun onAuthorizationError() {
          paymentDeclinedModel.show(childFragmentManager, "paymentDeclinedModel")
        }
      })
    }

    setProduct()

  }

  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume ProductFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause ProductFragment")
  }

  private fun setProduct() {

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    binding.txtProdName.text = viewModel?.actualProduct?.value?.title?.capitalizeWords()
    binding.txtProdPrice.text = "$${viewModel?.actualProduct?.value?.price}"
    binding.txtProdDescricao.text = viewModel?.actualProduct?.value?.description?.capitalize()

    val prodImage = binding.imgProd
    val image = viewModel?.actualProduct?.value?.url

    Picasso.get().load(image).into(prodImage)

  }
}