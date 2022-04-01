package com.example.myoutlet.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.databinding.CardItemBinding
import com.example.myoutlet.databinding.FragmentListBinding
import com.example.myoutlet.model.Cards
import com.squareup.picasso.Picasso

private lateinit var binding: CardItemBinding
private var _binding: FragmentListBinding? = null
private val fragmentBinding get() = _binding!!

class MyAdapterListFragment : Fragment() {

  companion object {
    fun newInstance() = MyAdapterListFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentListBinding.inflate(layoutInflater, container, false)
    val view = binding.root

    val activity = activity as Context
    val recyclerView = fragmentBinding.recyclerView
    recyclerView.layoutManager = LinearLayoutManager(activity)
    recyclerView.adapter = MyAdapter(arrayListOf(Cards()))

    return view
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d("wwwd", "onAttachs MyAdapter")
  }


  internal inner class MyAdapter(private val cardList: ArrayList<Cards>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

      binding = CardItemBinding.inflate(LayoutInflater.from(parent.context))
      val view = binding.root
      return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val currentitem = cardList[position]

      holder.title.text = currentitem.title?.uppercase()
      val key = currentitem.key
      key.toString()
      val imageTarget = currentitem.url
      Picasso.get().load(imageTarget).into(holder.url)

      holder.itemView.setOnClickListener {

      }

    }

    override fun getItemCount(): Int {
      return cardList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      val title: TextView = binding.txtInformate
      val url: ImageView = binding.imgMain
    }
  }
}