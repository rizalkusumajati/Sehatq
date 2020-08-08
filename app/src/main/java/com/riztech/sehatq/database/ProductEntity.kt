package com.riztech.sehatq.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val idLocal: Int = 0,
    val id: Int?,
    val imageUrl: String?,
    val title: String?,
    val description: String?,
    val price: String?,
    val loved: Int?

)