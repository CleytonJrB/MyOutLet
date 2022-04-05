package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myoutlet.R
import com.example.myoutlet.databinding.FragmentListBinding

class homeProductFragment : Fragment() {

  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!

  companion object {
    fun newInstance() = homeProductFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {

    _binding = FragmentListBinding.inflate(inflater, container, false)
    val view = binding.root
    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnNew.setOnClickListener {
      findNavController().navigate(R.id.fromHomeProductFragmenttoCateFragment)
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