package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.adapters.CartAdapter
import com.example.myoutlet.databinding.CartModalBinding
import com.example.myoutlet.interfaces.OrderResponseCallBack
import com.example.myoutlet.klarna.KlarnaSingleton
import com.example.myoutlet.klarna.klarnaReponse.PaymentDeclined
import com.example.myoutlet.klarna.klarnaReponse.PaymentSuccess
import com.example.myoutlet.model.CateItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartModal : BottomSheetDialogFragment() {

  private var _binding: CartModalBinding? = null
  private val binding get() = _binding!!

  private lateinit var cartRecyclerView: RecyclerView
  private lateinit var cartArrayList: List<CateItem>
  private lateinit var gridLayoutManager: GridLayoutManager

  private lateinit var cartAdapter: CartAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
//    dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    _binding = CartModalBinding.inflate(inflater, container, false)
    binding.recyclerViewCartModal.visibility = View.GONE
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val paymentSuccessModel = PaymentSuccess()
    val paymentDeclinedModel = PaymentDeclined()

    cartAdapter = CartAdapter()
    cartRecyclerView = binding.recyclerViewCartModal
    gridLayoutManager = GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)
    cartRecyclerView.layoutManager = gridLayoutManager
    cartRecyclerView.adapter = cartAdapter
    cartArrayList = ArrayList()

    if(MyOutLetBridge.viewModel?.actualProduct?.value == null){
      binding.txtCartEnable.visibility = View.VISIBLE
    }
    binding.recyclerViewCartModal.visibility = View.VISIBLE
    cartAdapter.setData(MyOutLetBridge.viewModel?.actualProduct?.value!!)

    binding.btnKlarnaP.setOnClickListener {
      KlarnaSingleton.showPaymentView(object : OrderResponseCallBack {
        override fun onAuthorizationSuccess() {
          MyOutLetBridge.viewModel!!.actualProduct.value!!.clear()
          paymentSuccessModel.show(childFragmentManager, "paymentSuccessModel")
        }
        override fun onAuthorizationError() {
          MyOutLetBridge.viewModel!!.actualProduct.value!!.clear()
          paymentDeclinedModel.show(childFragmentManager, "paymentDeclinedModel")
        }
      })

    }

  }
}