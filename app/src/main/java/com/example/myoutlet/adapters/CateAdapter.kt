package com.example.myoutlet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.databinding.CardViewCateBinding
import com.example.myoutlet.model.CateItem
import com.squareup.picasso.Picasso

class CateAdapter(
  private val catArrayList: ArrayList<CateItem>,
  private val listener: OnItemClickListener
) :
  RecyclerView.Adapter<CateAdapter.CateHolder>() {

  private var _binding: CardViewCateBinding? = null
  private val binding get() = _binding!!

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateHolder {

    _binding = CardViewCateBinding.inflate(LayoutInflater.from(parent.context))
    val view = binding.root
    return CateHolder(view)

  }

  override fun onBindViewHolder(holder: CateHolder, position: Int) {
    val cateAdapter = catArrayList[position]

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

  inner class CateHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val tittle: TextView = binding.txtTitle
    var price: TextView = binding.txtPrice
    var url: ImageView = binding.imgCate

    init {
      itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
      val position= adapterPosition
      if(position!= RecyclerView.NO_POSITION) {
        listener.onItemClick(position)
      }
    }
  }

  interface OnItemClickListener {
    fun onItemClick(position: Int)
  }
}

