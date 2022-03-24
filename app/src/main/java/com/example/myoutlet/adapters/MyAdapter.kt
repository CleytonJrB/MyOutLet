package com.example.myoutlet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.R
import com.example.myoutlet.databinding.CardItemBinding
import com.example.myoutlet.databinding.CardViewCateBinding
import com.example.myoutlet.model.Cards
import com.squareup.picasso.Picasso

private lateinit var binding: CardItemBinding

class MyAdapter( private val cardList:ArrayList<Cards>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

    binding = CardItemBinding.inflate(LayoutInflater.from(parent.context))
    val view = binding.root
//    val itemView = LayoutInflater.from(context).inflate(
//      R.layout.card_item,
//    parent,false)
    return MyViewHolder(view)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentitem = cardList[position]

    holder.title.text = currentitem.title?.uppercase()
    val key = currentitem.key
    key.toString()
    val imageTarget = currentitem.url
    Picasso.get().load(imageTarget).into(holder.url)

  }

  override fun getItemCount(): Int {
    return cardList.size
  }
  class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    val title : TextView = binding.txtInformate
    val url : ImageView = binding.imgMain
  }
}