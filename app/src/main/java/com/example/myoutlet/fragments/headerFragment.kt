package com.example.myoutlet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myoutlet.HeaderBridge
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.ProductRepository
import com.example.myoutlet.ProductViewModel
import com.example.myoutlet.databinding.FragmentHeaderBinding

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

    HeaderBridge.viewModelBridge = viewModel

    val cartModal = CartModal()

//
//    HeaderBridge.viewModelBridge!!.actualProduct.observe(viewLifecycleOwner) { products ->
//      println("TESTE HEADER TAMANHO " + products.size)
//      binding.contCartNumber.text = products.size.toString()
//    }

    binding.cartIcon.setOnClickListener {
      cartModal.show(childFragmentManager, "cart_modal")
    }
    binding.btnBackPress.setOnClickListener {
      activity!!.onBackPressed()
    }
  }
}