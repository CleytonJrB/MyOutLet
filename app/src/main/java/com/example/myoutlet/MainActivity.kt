package com.example.myoutlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.example.myoutlet.databinding.PgMainBinding
import com.example.myoutlet.fragments.cateFragment
import com.example.myoutlet.fragments.homeProductFragment
import com.example.myoutlet.model.Cards
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var dbref : DatabaseReference
  private lateinit var cardRecyclerview: RecyclerView
  private lateinit var cardArrayList : ArrayList<Cards>

  private var _binding: PgMainBinding? = null
  private val binding get() = _binding!!

  private lateinit var listProduct: homeProductFragment
  private lateinit var cateFragment: cateFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = PgMainBinding.inflate(layoutInflater)
    var view = binding.root
    setContentView(view)

    binding.btnNew.setOnClickListener(this)

    listProduct = homeProductFragment()
    cateFragment = cateFragment(binding)

    Log.d("wwwd", "onCreate View MainActivity")

    if (savedInstanceState == null) {

      setFragment(listProduct)

    }
  }

//    if (savedInstanceState == null) {
//      supportFragmentManager
//        .beginTransaction()
//        .replace(R.id.container_root, DeatailsFragment.newInstance())
//        .commit()
////      getCardData()
//    }


    //    binding.btnNew.setOnClickListener {
////      val intent = Intent( this,CateActivity::class.java )
////      startActivity(intent)
//
//      val args = Bundle()
//
//      val fragment = DeatailsFragment.newInstance()
//      fragment.arguments = args
//      supportFragmentManager
//        .beginTransaction()
//        .replace(R.id.container_root,fragment,"fragmentDetail")
//        .addToBackStack(null)
//        .commit()
//
//    }
//
//    cardRecyclerview = binding.cardList
//    cardRecyclerview.layoutManager = LinearLayoutManager(this)
//    cardRecyclerview.setHasFixedSize(true)
//
//    cardArrayList = arrayListOf<Cards>()
//    getCardData()
//  }
//


    private fun setFragment(fragment: Fragment) {
      val fragmentTransaction = supportFragmentManager.beginTransaction()
      fragmentTransaction.replace(R.id.container_root, fragment)
      fragmentTransaction.commit()
      fragmentTransaction.addToBackStack(null)
    }

    override fun onClick(v: View?) {
      when (v?.id) {
        R.id.btn_new -> {
          setFragment(cateFragment)
        }
      }
    }
}