package com.example.myoutlet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myoutlet.model.ModalState
import com.example.myoutlet.model.CateItem

internal class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

  val modalState = MutableLiveData(ModalState.ON_CHOOSING)
  val products = MutableLiveData<MutableList<CateItem>>()

  val contNumber = MutableLiveData<Int>()

  fun addProductToCart(product: CateItem){
    var newValue: MutableList<CateItem> = mutableListOf()
    products.value?.forEach { product -> newValue.add(product) }
    newValue.add(product)
    products.value = newValue
  }


  class MainViewModelFactory(private val repositorySDK: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return ProductViewModel(repositorySDK) as T
    }
  }

}