package com.example.myoutlet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val cardList:ArrayList<Cards>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item,
    parent,false)
    return MyViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentitem = cardList[position]

    holder.informate.text = currentitem.informate
  }

  override fun getItemCount(): Int {
    return cardList.size
  }
  class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    val informate : TextView = itemView.findViewById(R.id.txt_informate)

  }
}