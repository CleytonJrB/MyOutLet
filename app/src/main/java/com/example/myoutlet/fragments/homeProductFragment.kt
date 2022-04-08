package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.adapters.MyAdapter
import com.example.myoutlet.databinding.FragmentListBinding
import com.example.myoutlet.model.Cards
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class homeProductFragment : Fragment() {

  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!

  private lateinit var cardRecyclerview: RecyclerView
  private lateinit var cardArrayList: ArrayList<Cards>

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

    getAllProduct()

    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnNew.setOnClickListener {
      findNavController().navigate(R.id.fromHomeProductFragmenttoCateFragment)
    }

    cardRecyclerview = binding.recyclerView
    cardRecyclerview.setHasFixedSize(true)

    cardArrayList = arrayListOf<Cards>()


  }

  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume homeProductFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause homeProductFragment")
  }

  private fun getAllProduct() {

    var dbb= FirebaseDatabase.getInstance().getReference("Product")
//
    dbb.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        if(snapshot.exists()){
          for(cardSnapshot in snapshot.children){
//
            val card =  cardSnapshot.getValue(Cards::class.java)

            cardArrayList.add(card!!)

          }

          cardRecyclerview.adapter = MyAdapter(cardArrayList)
          Log.d("dww", cardArrayList.toString())

        }

      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Error","Error")
      }
    })

  }

}