package com.riztech.sehatq.database

import com.riztech.sehatq.api.HomeApiService
import com.riztech.sehatq.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDAO: ProductDAO, private val apiService: HomeApiService){

    suspend fun getProducts() : List<ProductEntity> = productDAO.getProducts()
    suspend fun insertUser(productEntity: ProductEntity) = productDAO.insert(productEntity)

    fun getApiService(): HomeApiService = apiService
}