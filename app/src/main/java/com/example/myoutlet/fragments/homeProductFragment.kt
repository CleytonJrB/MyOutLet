package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myoutlet.databinding.FragmentListBinding

class homeProductFragment : Fragment() {

  private var _binding: FragmentListBinding? = null
  private val fragmentBinding get() = _binding!!

  companion object {
    fun newInstance() = homeProductFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentListBinding.inflate(layoutInflater, container, false)
    val view = fragmentBinding.root
    return view
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