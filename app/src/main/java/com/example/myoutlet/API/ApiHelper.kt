package com.example.myoutlet.API

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.adapters.CateAdapter
import com.example.myoutlet.adapters.MyAdapter
import com.example.myoutlet.model.Cards
import com.example.myoutlet.model.CateItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

internal object ApiHelper {

  fun getAllProductFirebase(recyclerView: RecyclerView, list: ArrayList<Cards>) {

    val dbb = FirebaseDatabase.getInstance().getReference("Product")

    dbb.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        if (snapshot.exists()) {
          for (cardSnapshot in snapshot.children) {

            val card = cardSnapshot.getValue(Cards::class.java)
            list.add(card!!)
          }
          recyclerView.adapter = MyAdapter(list)
        }
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Error", "Error GetAllProduct")
      }
    })
  }

  fun getProductDataFirebase(
    onItemClickListener: CateAdapter.OnItemClickListener,
    list: ArrayList<CateItem>,
    recyclerView: RecyclerView
  ) {

    val dbb = FirebaseDatabase.getInstance().getReference("Product")

    dbb.addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        if (snapshot.exists()) {
          for (cardSnapshot in snapshot.children) {

            val prod = cardSnapshot.getValue(CateItem::class.java)

            list.add(prod!!)

          }
          recyclerView.adapter = CateAdapter(list, onItemClickListener)
        }
      }

      override fun onCancelled(error: DatabaseError) {
        Log.d("Error", "Error in read GetProduct")
      }
    })
  }
}