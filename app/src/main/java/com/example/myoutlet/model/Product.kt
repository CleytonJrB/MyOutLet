package com.example.myoutlet.model

import java.io.Serializable

data class Product(
  var title : String,
  var url : String,
  var price : String,
  var description : String,
  ):Serializable
