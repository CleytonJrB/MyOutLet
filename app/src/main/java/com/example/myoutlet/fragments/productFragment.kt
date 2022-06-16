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
import com.example.myoutlet.databinding.FragmentHeaderBinding
import com.example.myoutlet.databinding.PgProductBinding
import com.example.myoutlet.interfaces.OrderResponseCallBack
import com.example.myoutlet.klarna.KlarnaSingleton
import com.example.myoutlet.klarna.klarnaReponse.PaymentDeclined
import com.example.myoutlet.klarna.klarnaReponse.PaymentSuccess
import com.example.myoutlet.model.CateItem
import com.example.myoutlet.model.Product
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

    binding.btnSearch.setOnClickListener {

      findNavController().navigate(R.id.fromProductFragmenttoMapFragment)
    }
    binding.btnCheckout.setOnClickListener {

      MyOutLetBridge.viewModel?.addProductToCart(CateItem(
        title = args.product.title,
        price = args.product.price,
        url = args.product.url,
      ))
    }

    setProduct()

    viewModel?.actualProduct?.observe(viewLifecycleOwner){ products ->
      viewModel.contNumber.value = products.size
      binding.txtProdSize.text = products.size.toString()
    }

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

    binding.txtProdName.text = args.product.title.capitalizeWords()
    binding.txtProdPrice.text = "$${args.product.price.capitalizeWords()}"
    binding.txtProdDescricao.text = args.product.description.capitalize()

    val prodImage = binding.imgProd
    val image = args.product.url

    Picasso.get().load(image).into(prodImage)

  }
}