package com.example.myoutlet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.model.CateItem

class CateAdapter(var context : Context, var catArrayList: ArrayList<CateItem>) : RecyclerView.Adapter<CateAdapter.CateHolder>() {

  private lateinit var cListener : onItemOnClickListener

  interface onItemOnClickListener{
    fun onItemClick(position: Int)
  }

  fun setOnItemClickListener(listener:onItemOnClickListener){
    cListener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateHolder {

    val cateHolder = LayoutInflater.from(parent.context).inflate(R.layout.card_view_cate,parent,false)

    return CateHolder(cateHolder,cListener)

  }

  override fun onBindViewHolder(holder: CateHolder, position: Int) {
    var cateAdapter : CateItem = catArrayList[position]

    holder.tittle.text = cateAdapter.descricao
    holder.price.text = cateAdapter.price

  }

  override fun getItemCount(): Int {
    return catArrayList.size
  }

  class CateHolder(itemView:View, listener:onItemOnClickListener) : RecyclerView.ViewHolder(itemView){

    var tittle : TextView = itemView.findViewById<TextView>(R.id.txt_cate)
    var price : TextView = itemView.findViewById<TextView>(R.id.txt_price)

    init{
      itemView.setOnClickListener {
        listener.onItemClick(adapterPosition)

      }
    }

  }


}