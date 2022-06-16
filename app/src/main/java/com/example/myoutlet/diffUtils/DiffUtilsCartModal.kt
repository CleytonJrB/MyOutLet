package com.example.myoutlet.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.myoutlet.model.CateItem

class DiffUtilsCartModal(
  private val oldCartList: List<CateItem>,
  private val newCartList: List<CateItem>
) :
  DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldCartList.size
  }

  override fun getNewListSize(): Int {
    return newCartList.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldCartList[oldItemPosition].title == newCartList[newItemPosition].title
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return when {
      oldCartList[oldItemPosition].title != newCartList[newItemPosition].title -> false
      oldCartList[oldItemPosition].price != newCartList[newItemPosition].price -> false
      oldCartList[oldItemPosition].description != newCartList[newItemPosition].description -> false
      oldCartList[oldItemPosition].adress != newCartList[newItemPosition].adress -> false
      oldCartList[oldItemPosition].location != newCartList[newItemPosition].location -> false
      oldCartList[oldItemPosition].store != newCartList[newItemPosition].store -> false
      else -> true
    }
  }
}