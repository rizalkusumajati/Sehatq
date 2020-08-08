package com.riztech.sehatq.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.riztech.sehatq.model.Product

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products ORDER BY idLocal DESC")
    suspend fun getProducts():List<ProductEntity>
}