package com.riztech.sehatq.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riztech.sehatq.database.ProductEntity
import com.riztech.sehatq.database.ProductRepository
import com.riztech.sehatq.model.Product
import kotlinx.coroutines.*

class HistoryViewModel @ViewModelInject constructor(val productRepository: ProductRepository) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val listData by lazy { MutableLiveData<ArrayList<Product>>() }
    fun getUser() {
        uiScope.launch {
            val listEntity = getProductDB()
            var lisItem = arrayListOf<Product>()
            for (i in listEntity){
                val product = Product(i.id, i.imageUrl, i.title, i.price, i.description, i.loved)
                lisItem.add(product)
            }
            listData.value = lisItem
        }
    }

    suspend fun getProductDB(): List<ProductEntity>{
        return withContext(Dispatchers.IO){
            productRepository.getProducts()
        }
    }
}