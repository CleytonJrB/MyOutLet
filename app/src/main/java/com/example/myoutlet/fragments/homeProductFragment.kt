package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.API.ApiHelper.getAllProductFirebase
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.ProductRepository
import com.example.myoutlet.ProductViewModel
import com.example.myoutlet.R
import com.example.myoutlet.databinding.FragmentListBinding
import com.example.myoutlet.model.Cards
import com.example.myoutlet.model.ModalState

class HomeProductFragment : Fragment() {

  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: ProductViewModel

  private lateinit var cardRecyclerview: RecyclerView
  private lateinit var cardArrayList: ArrayList<Cards>

  companion object {
    fun newInstance() = HomeProductFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {

    _binding = FragmentListBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnNew.setOnClickListener {
      findNavController().navigate(R.id.fromHomeProductFragmenttoCateFragment)
    }

    cardRecyclerview = binding.recyclerView
    cardRecyclerview.setHasFixedSize(true)

    cardArrayList = arrayListOf<Cards>()

    getAllProductFirebase(cardRecyclerview, cardArrayList)

  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(
      this,
      ProductViewModel.MainViewModelFactory(ProductRepository())
    ).get(ProductViewModel::class.java)

    MyOutLetBridge.viewModel = viewModel

    viewModel.modalState.observe(viewLifecycleOwner) { state ->
      println(state.toString())
      if (state == ModalState.ON_SUCCESS) {
//          findNavController().navigate(R.id.fromPaymentSuccesstoProductFragment)
        Log.d("ProductFragment", "onActivityCreated: ON_SUCCESS")
      }
      if (state == ModalState.ON_ERROR) {
        Log.d("ProductFragment", "onActivityCreated: ON_ERROR")
      }
      if (state == ModalState.ON_LOADING) {
        findNavController().navigate(R.id.homeProductFragment)
//          findNavController().navigate(R.id.fromPaymentSuccesstoProductFragment)
        Log.d("ProductFragment", "onActivityCreated: ON_CHOOSING")
      }
    }
  }


  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume homeProductFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause homeProductFragment")
  }

}