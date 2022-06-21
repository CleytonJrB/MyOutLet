package com.example.myoutlet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.MyOutLetBridge
import com.example.myoutlet.databinding.CardCartModalBinding
import com.example.myoutlet.model.CateItem
import com.squareup.picasso.Picasso

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartHolder>() {

  private var _binding: CardCartModalBinding? = null
  private val binding get() = _binding!!

  private var cartList: MutableList<CateItem> = mutableListOf()
//  emptyList<CateItem>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
    _binding = CardCartModalBinding.inflate(LayoutInflater.from(parent.context))
    val view = binding.root
    return CartHolder(view)
  }

  override fun onBindViewHolder(holder: CartHolder, position: Int) {
    val cartItem = cartList[position]
    val priceCart = cartItem.price

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    holder.name.text = cartItem.title?.capitalizeWords()
    holder.price.text = "$${priceCart}"
    val imageTarget = cartItem.url
    Picasso.get().load(imageTarget).into(holder.url)

    holder.remove.setOnClickListener {
      removeAt(position)
      println(position+1) }
  }

  override fun getItemCount(): Int {
    return cartList.size
  }

  fun setData(newCartList: MutableList<CateItem>) {
    cartList = newCartList
    notifyDataSetChanged()

//    val diffUtil = DiffUtilsCartModal(cartList, newCartList)
//    val diffResult = DiffUtil.calculateDiff(diffUtil)
//    cartList = newCartList
//    diffResult.dispatchUpdatesTo(this)
  }

  private fun removeAt(position: Int) {
    cartList.removeAt(position)
    notifyItemRemoved(position)
    notifyItemRangeChanged(position, cartList.size)
  }

  inner class CartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = binding.txtCartModalName
    val price: TextView = binding.txtCartModalPrice
    var url: ImageView = binding.imageViewCartModal
    val remove: ImageView = binding.btnCartModalRemove

  }
}
