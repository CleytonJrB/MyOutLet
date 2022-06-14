package com.example.myoutlet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myoutlet.model.ModalState
import com.example.myoutlet.model.Product

internal class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

  val modalState = MutableLiveData(ModalState.ON_CHOOSING)
  val actualProduct = MutableLiveData<Product>()


  class MainViewModelFactory(private val repositorySDK: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return ProductViewModel(repositorySDK) as T
    }
  }

}