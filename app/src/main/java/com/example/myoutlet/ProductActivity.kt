package com.example.myoutlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_product)

    setProduct()


  }

  private fun setProduct(){

    val prodName = findViewById<TextView>(R.id.txt_prod_name)
    val prodPreco = findViewById<TextView>(R.id.txt_prod_price)
    val prodImag = findViewById<ImageView>(R.id.img_prod)
    val prodDescr = findViewById<TextView>(R.id.txt_prod_descricao)

    val dados = intent.extras

    val name = dados?.getString("name")
    val descricao = dados?.getString("descricao")
    val preco = dados?.getString("preco")
    val image = dados?.getString("image")
    val mapLoc = dados?.getString("location")
    val mapAdress = dados?.getString("adress")
    val mapStore = dados?.getString("store")


    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    prodName.text = name?.capitalizeWords()
    prodPreco.text = "$${preco}"
    prodDescr.text = descricao?.capitalize()


    val imageProdTarget = image
    Picasso.get().load(imageProdTarget).into(prodImag)

    val OnCLick = findViewById<Button>(R.id.btn_search)

    OnCLick.setOnClickListener {
      val intent = Intent(this,MapActivity::class.java)

      intent.putExtra("mapStore", mapStore)
      intent.putExtra("mapLoc", mapLoc)
      intent.putExtra("mapAdress", mapAdress)

      startActivity(intent)
    }


  }

}