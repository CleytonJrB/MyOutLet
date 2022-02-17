package com.example.myoutlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.adapters.CateAdapter
import com.example.myoutlet.model.CateItem

class CateActivity : AppCompatActivity() {

  private var recyclerView : RecyclerView ?= null
  private var gridLayoutManager : GridLayoutManager ?= null
  private var cateArrayList : ArrayList<CateItem> ?= null
  private var cateAdapter : CateAdapter ?= null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_cate)

    recyclerView = findViewById(R.id.recycle_cate)
    gridLayoutManager = GridLayoutManager(applicationContext,2,LinearLayoutManager.VERTICAL,false)
    recyclerView?.layoutManager = gridLayoutManager
    recyclerView?.setHasFixedSize(true)
    cateArrayList = ArrayList()
    cateArrayList = setDataList()
    cateAdapter = CateAdapter(applicationContext, cateArrayList!!)
    recyclerView?.adapter = cateAdapter
    getCateData()


  }

  private  fun setDataList() : ArrayList<CateItem>{

    var items : ArrayList<CateItem> = ArrayList()

    items.add(CateItem("Djonga","69"))
    items.add(CateItem("Filipe Ret","85"))
    items.add(CateItem("Delacruz","38"))
    items.add(CateItem("Vitao","81"))
    items.add(CateItem("Emicida","13"))

    return items

  }

  private fun getCateData(){
    cateAdapter?.setOnItemClickListener(object : CateAdapter.onItemOnClickListener{
      override fun onItemClick(position: Int) {
//        Toast.makeText(this@CateActivity,"MAIN CATEGORY IS ${position+1}",Toast.LENGTH_LONG).show()
        val intent = Intent( this@CateActivity,ProductActivity::class.java )
        startActivity(intent)
      }

    })

  }

}