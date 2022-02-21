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


//    ERROR - Attempt to invoke virtual method 'void android.widget.Button.setOnClickListener(android.view.View$OnClickListener)' on a null object reference
// Não estou conseguindo fazer a navegação para Activity MapActivit
//
//    val OnCLick = findViewById<Button>(R.id.btn_search)
//
//    OnCLick.setOnClickListener {
//      val ProdctMap = Intent(this,MapActivity::class.java)
//
//      startActivity(ProdctMap)
//    }

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
    val prodLoc = dados?.getString("location")
    val prodAdress = dados?.getString("adress")



    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    prodName.text = name?.capitalizeWords()
    prodPreco.text = "$${preco}"
    prodDescr.text = descricao?.capitalize()


    val imageProdTarget = image
    Picasso.get().load(imageProdTarget).into(prodImag)

  }

}