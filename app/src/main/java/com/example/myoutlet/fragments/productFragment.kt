package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myoutlet.R
import com.example.myoutlet.databinding.PgProductBinding
import com.example.myoutlet.model.Product
import com.squareup.picasso.Picasso

class productFragment : Fragment() {

  private var _binding: PgProductBinding? = null
  private val binding get() = _binding!!

  companion object {
    fun newInstance() = productFragment()
  }

  private val args: productFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {

    _binding = PgProductBinding.inflate(inflater, container, false)
    val view = binding.root

    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnSearch.setOnClickListener {

      findNavController().navigate(R.id.fromProductFragmenttoMapFragment)
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

    binding.txtProdName.text = args.product.title.capitalizeWords()
    binding.txtProdPrice.text = "$${args.product.price}"
    binding.txtProdDescricao.text = args.product.description.capitalize()

    Log.d("setProduct", "${args.product.title},${args.product.price}, ${args.product.description} ")

    val prodImag = binding.imgProd
    val image = args.product.url

    val imageProdTarget = image
    Picasso.get().load(imageProdTarget).into(prodImag)

//
//    val prodName = binding.txtProdName
//    val prodPreco = binding.txtProdPrice
//    val prodImag = binding.imgProd
//    val prodDescr = binding.txtProdDescricao
//
//    val dados = intent.extras
//
//    val name = dados?.getString("name")
//    val descricao = dados?.getString("descricao")
//    val preco = dados?.getString("preco")
//    val image = dados?.getString("image")
//    val mapLoc = dados?.getString("location")
//    val mapAdress = dados?.getString("adress")
//    val mapStore = dados?.getString("store")
//
//
//    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
//
//    prodName.text = name?.capitalizeWords()
//    prodPreco.text = "$${preco}"
//    prodDescr.text = descricao?.capitalize()
//
//
//    val imageProdTarget = image
//    Picasso.get().load(imageProdTarget).into(prodImag)
//
//    val OnCLick = binding.btnSearch
//
//    OnCLick.setOnClickListener {
//      val intent = Intent(this,MapActivity::class.java)
//
//      intent.putExtra("mapStore", mapStore)
//      intent.putExtra("mapLoc", mapLoc)
//      intent.putExtra("mapAdress", mapAdress)
//
//      startActivity(intent)
//    }
//
//
  }

}