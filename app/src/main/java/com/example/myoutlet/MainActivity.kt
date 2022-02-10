package com.example.myoutlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

  private lateinit var dbref : DatabaseReference
  private lateinit var cardRecyclerView: RecyclerView
  private lateinit var cardArrayList : ArrayList<Cards>


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_main)

    cardRecyclerView = findViewById(R.id.cardList)
    cardRecyclerView.layoutManager = LinearLayoutManager(this)
    cardRecyclerView.setHasFixedSize(true)

    cardArrayList = arrayListOf<Cards>()
    getCardData()


//    val clickme = findViewById<Button>(R.id.btn_singin)
//
//    clickme.setOnClickListener {
//      val intent = Intent( this, LoginActivity::class.java)
//      startActivity(intent)
//    }
  }

  private fun getCardData() {
    dbref = FirebaseDatabase.getInstance().getReference("Cards")

    dbref.addValueEventListener(object :ValueEventListener{
      override fun onDataChange(snapshot: DataSnapshot) {

        if(snapshot.exists()){
          for(cardsSnapshot in snapshot.children){

            val card =  cardsSnapshot.getValue(Cards::class.java)
            cardArrayList.add(card!!)
          }

          cardRecyclerView.adapter = MyAdapter(cardArrayList)

        }

      }

      override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
      }

    })
  }
}