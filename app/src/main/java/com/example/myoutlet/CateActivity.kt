package com.example.myoutlet

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.adapters.CateAdapter
import com.example.myoutlet.adapters.MyAdapter
import com.example.myoutlet.databinding.PgCateBinding
import com.example.myoutlet.databinding.PgMapBinding
import com.example.myoutlet.model.CateItem
import com.google.firebase.database.*

private lateinit var binding: PgCateBinding

class CateActivity : AppCompatActivity() {

  private var dbref : DatabaseReference ?= null
  private var recyclerView : RecyclerView ?= null
  private var gridLayoutManager : GridLayoutManager ?= null
  private var cateArrayList : ArrayList<CateItem> ?= null
  private var cateAdapter : CateAdapter ?= null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PgCateBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    recyclerView = binding.recycleCate
    gridLayoutManager = GridLayoutManager(applicationContext,2,LinearLayoutManager.VERTICAL,false)
    recyclerView?.layoutManager = gridLayoutManager
    recyclerView?.setHasFixedSize(true)
    cateArrayList = ArrayList()
    cateAdapter = CateAdapter(applicationContext, cateArrayList!!)
    recyclerView?.adapter = cateAdapter
    getProductData()

  }

  private fun getProductData(){

    dbref = FirebaseDatabase
      .getInstance().getReference("Product")

    dbref?.addValueEventListener(object :ValueEventListener{
      override fun onDataChange(snapshot: DataSnapshot) {
        if (snapshot.exists()){
          for (cardSnapshot in snapshot.children){

            val prod = cardSnapshot.getValue(CateItem::class.java)

            cateArrayList?.add(prod!!)

          }
          cateAdapter
        }
      }

      override fun onCancelled(error: DatabaseError) {
        Toast.makeText(this@CateActivity,"Error in read Product",Toast.LENGTH_LONG).show()
      }
    })


    cateAdapter?.setOnItemClickListener(object : CateAdapter.onItemOnClickListener{
      override fun onItemClick(position: Int) {
        val intent = Intent( this@CateActivity,ProductActivity::class.java )

        var data = cateArrayList?.get(position)

        intent.putExtra("name",data?.title)
        intent.putExtra("preco",data?.price)
        intent.putExtra("descricao",data?.description)
        intent.putExtra("image",data?.url)
        intent.putExtra("adress",data?.adress)
        intent.putExtra("location",data?.location)
        intent.putExtra("store",data?.store)

        startActivity(intent)
      }

    })
  }

}