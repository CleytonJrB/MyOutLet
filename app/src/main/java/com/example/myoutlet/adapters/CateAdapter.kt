package com.example.myoutlet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.model.CateItem
import com.squareup.picasso.Picasso

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
    val cateAdapter : CateItem = catArrayList[position]

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    val priceCate = cateAdapter.price

    holder.tittle.text = cateAdapter.title?.capitalizeWords()
    holder.price.text = "$${priceCate}"

    val imageTarget = cateAdapter.url
    Picasso.get().load(imageTarget).into(holder.url)

  }

  override fun getItemCount(): Int {
    return catArrayList.size
  }

  class CateHolder(itemView:View, listener:onItemOnClickListener) : RecyclerView.ViewHolder(itemView){

    var tittle : TextView = itemView.findViewById(R.id.txt_cate)
    var price : TextView = itemView.findViewById(R.id.txt_price)
    var url : ImageView = itemView.findViewById(R.id.img_cate)

    init{
      itemView.setOnClickListener {
        listener.onItemClick(adapterPosition)

      }
    }

  }


}