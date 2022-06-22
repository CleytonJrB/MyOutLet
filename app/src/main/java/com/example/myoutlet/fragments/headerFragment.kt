package com.example.myoutlet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.ProductRepository
import com.example.myoutlet.ProductViewModel
import com.example.myoutlet.databinding.FragmentHeaderBinding
import com.example.myoutlet.interfaces.OrderResponseCallBack
import com.example.myoutlet.klarna.KlarnaSingleton
import com.example.myoutlet.klarna.klarnaReponse.PaymentDeclined
import com.example.myoutlet.klarna.klarnaReponse.PaymentSuccess

class HeaderFragment : Fragment() {

  private var _binding: FragmentHeaderBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: ProductViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentHeaderBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(
      this,
      ProductViewModel.MainViewModelFactory(ProductRepository())
    ).get(ProductViewModel::class.java)

    MyOutLetBridge.viewModel = viewModel

    val cartModal = CartModal()

    binding.contCartNumber.visibility = View.INVISIBLE
    println("TESTEPRODUCTS FORA ${MyOutLetBridge.viewModel!!.products.value?.size}")

    MyOutLetBridge.viewModel?.products?.observe(viewLifecycleOwner) { products ->
      if(MyOutLetBridge.viewModel!!.products.value?.size == 0){
        binding.contCartNumber.visibility = View.INVISIBLE
      }
      println("TESTEPRODUCTS  DENTRO ${MyOutLetBridge.viewModel!!.products.value?.size}")

      if (products.size >= 1) {
        binding.contCartNumber.visibility = View.VISIBLE
        binding.contCartNumber.text = products.size.toString()
        println("TESTE HEADER TAMANHO " +products.size.toString())
      }
    }


    binding.txtHeader.setOnClickListener {
    }
    binding.btnBackPress.setOnClickListener {
      activity!!.onBackPressed()
    }
    binding.cartIcon.setOnClickListener {
      if(!cartModal.isVisible) cartModal.show(childFragmentManager, "cart_modal")
    }
  }
}