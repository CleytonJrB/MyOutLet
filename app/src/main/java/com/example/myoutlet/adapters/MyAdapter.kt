package com.example.myoutlet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.model.Cards
import com.squareup.picasso.Picasso

class MyAdapter(private val cardList:ArrayList<Cards>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
      R.layout.card_item,
    parent,false)
    return MyViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentitem = cardList[position]

    holder.title.text = currentitem.title?.uppercase()

    val imageTarget = currentitem.url
    Picasso.get().load(imageTarget).into(holder.url)

  }

  override fun getItemCount(): Int {
    return cardList.size
  }
  class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    val title : TextView = itemView.findViewById(R.id.txt_informate)
    val url : ImageView = itemView.findViewById(R.id.img_main)
  }
}