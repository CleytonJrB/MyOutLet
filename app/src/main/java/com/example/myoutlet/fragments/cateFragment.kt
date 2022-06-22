package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.API.ApiHelper.getProductDataFirebase
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.adapters.CateAdapter
import com.example.myoutlet.databinding.PgCateBinding
import com.example.myoutlet.model.CateItem
import com.example.myoutlet.model.Product

class CateFragment : Fragment(), CateAdapter.OnItemClickListener {

  private var _binding: PgCateBinding? = null
  private val binding get() = _binding!!

  private lateinit var recyclerView: RecyclerView
  private lateinit var gridLayoutManager: GridLayoutManager
  private lateinit var cateArrayList: ArrayList<CateItem>

  companion object {
    fun newInstance() = CateFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = PgCateBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    recyclerView = binding.recycleCate
    gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = gridLayoutManager
    recyclerView.setHasFixedSize(true)
    cateArrayList = arrayListOf<CateItem>()

    getProductDataFirebase(this@CateFragment, cateArrayList, recyclerView)

  }

  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume CateFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause CateFragment")
  }

  override fun onItemClick(position: Int, productInfo: CateItem) {

    val data = cateArrayList[position]

//    val teste = productInfo.also { teste ->
//      teste.title = "TESTE TESTEasdas"
//      teste.price = "TESASADSD QWE asdas"
//    }
//
//    Log.d("LOGCATEFRAGMENT"," : $teste")

    val action = CateFragmentDirections.fromCateFragmenttoProductFragment(
      product = Product(
        "${data.title}",
        "${data.url}",
        "${data.price}",
        "${data.description}"
      )
    )
    findNavController().navigate(action)
  }
}