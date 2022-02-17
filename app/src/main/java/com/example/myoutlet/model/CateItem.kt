package com.example.myoutlet.model

class CateItem {

  var descricao : String ?= null
  var price : String ?= null

  constructor(descricao: String?, price: String?) {
    this.descricao = descricao
    this.price = price
  }
}