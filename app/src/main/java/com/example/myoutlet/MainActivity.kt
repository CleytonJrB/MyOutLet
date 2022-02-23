package com.example.myoutlet

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myoutlet.adapters.MyAdapter
import com.example.myoutlet.model.Cards
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

  private lateinit var dbref : DatabaseReference
  private lateinit var cardRecyclerview: RecyclerView
  private lateinit var cardArrayList : ArrayList<Cards>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_main)

      val clickme = findViewById<ImageButton>(R.id.btn_new)

    clickme.setOnClickListener {
      val intent = Intent( this,CateActivity::class.java )
      startActivity(intent)
    }

    cardRecyclerview = findViewById(R.id.cardList)
    cardRecyclerview.layoutManager = LinearLayoutManager(this)
    cardRecyclerview.setHasFixedSize(true)

    cardArrayList = arrayListOf<Cards>()
    getCardData()
  }

  private fun getCardData() {

    dbref = FirebaseDatabase.getInstance().getReference("Product")

    dbref.addValueEventListener(object :ValueEventListener{
      override fun onDataChange(snapshot: DataSnapshot) {
        if(snapshot.exists()){
          for(cardSnapshot in snapshot.children){
            val card =  cardSnapshot.getValue(Cards::class.java)

            Log.d(TAG,"www Cards ${card}",null)

            cardArrayList.add(card!!)
          }

          cardRecyclerview.adapter = MyAdapter(cardArrayList)

        }

      }

      override fun onCancelled(error: DatabaseError) {
        Toast.makeText(this@MainActivity,"Error in read Product", Toast.LENGTH_LONG).show()      }
    })

  }
}