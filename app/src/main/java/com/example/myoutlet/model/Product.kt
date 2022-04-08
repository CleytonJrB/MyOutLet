package com.example.myoutlet.model

import java.io.Serializable

data class Product(
  val title : String,
  val url : String,
  val price : String,
  val description : String,
  ):Serializable
