package com.example.myoutlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myoutlet.databinding.PgProductBinding
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity() {

  private lateinit var binding: PgProductBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = PgProductBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    setProduct()

  }

  private fun setProduct(){

    val prodName = binding.txtProdName
    val prodPreco = binding.txtProdPrice
    val prodImag = binding.imgProd
    val prodDescr = binding.txtProdDescricao

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

    val OnCLick = binding.btnSearch

    OnCLick.setOnClickListener {
      val intent = Intent(this,MapActivity::class.java)

      intent.putExtra("mapStore", mapStore)
      intent.putExtra("mapLoc", mapLoc)
      intent.putExtra("mapAdress", mapAdress)

      startActivity(intent)
    }


  }

}