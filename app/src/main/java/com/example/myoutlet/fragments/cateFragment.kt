package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.adapters.CateAdapter
import com.example.myoutlet.databinding.PgCateBinding
import com.example.myoutlet.model.CateItem
import com.example.myoutlet.model.Product
import com.google.firebase.database.*

class cateFragment : Fragment(), CateAdapter.OnItemClickListener {

  private var _binding: PgCateBinding? = null
  private val binding get() = _binding!!

  private lateinit var recyclerView: RecyclerView
  private lateinit var gridLayoutManager: GridLayoutManager
  private lateinit var cateArrayList: ArrayList<CateItem>


  companion object {
    fun newInstance() = cateFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = PgCateBinding.inflate(inflater, container, false)
    val view = binding.root

    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    getProductData()

    recyclerView = binding.recycleCate
    gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = gridLayoutManager
    recyclerView.setHasFixedSize(true)
    cateArrayList = arrayListOf<CateItem>()

  }

  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume CateFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause CateFragment")
  }

  private fun getProductData() {

    var dbref = FirebaseDatabase
      .getInstance().getReference("Product")

    dbref.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        if (snapshot.exists()) {
          for (cardSnapshot in snapshot.children) {

            val prod = cardSnapshot.getValue(CateItem::class.java)

            cateArrayList.add(prod!!)

          }
          recyclerView.adapter = CateAdapter(cateArrayList, this@cateFragment)
        }
      }

      override fun onCancelled(error: DatabaseError) {
        Toast.makeText(context, "Error in read Product", Toast.LENGTH_LONG).show()
      }
    })


//    cateAdapter?.setOnItemClickListener(object : CateAdapter.onItemOnClickListener{
//      override fun onItemClick(position: Int) {
//        val intent = Intent( this@CateActivity,ProductActivity::class.java )
//
//       var data = cateArrayList?.get(position)
//
//        intent.putExtra("name",data?.title)
//        intent.putExtra("descricao",data?.description)
//       intent.putExtra("image",data?.url)
//        intent.putExtra("adress",data?.adress)
//        intent.putExtra("location",data?.location)
//        intent.putExtra("store",data?.store)
//
//        startActivity(intent)
//      }
//
//    })
  }

  override fun onItemClick(position: Int) {
    val data = cateArrayList[position]

    val action = cateFragmentDirections.fromCateFragmenttoProductFragment(
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