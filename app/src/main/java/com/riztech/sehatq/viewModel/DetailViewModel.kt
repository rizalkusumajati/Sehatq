package com.riztech.sehatq.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.riztech.sehatq.database.ProductEntity
import com.riztech.sehatq.database.ProductRepository
import com.riztech.sehatq.model.Product
import kotlinx.coroutines.*

class DetailViewModel @ViewModelInject constructor(val productRepository: ProductRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertProductsEntity(product: Product){
        uiScope.launch {
            val productEntity = ProductEntity(id = product.id, imageUrl = product.imageUrl, title = product.title, description = product.description, price = product.price, loved = product.loved)
            withContext(Dispatchers.IO){
                productRepository.insertUser(productEntity)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}