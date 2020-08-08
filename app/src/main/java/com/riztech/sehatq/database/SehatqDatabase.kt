package com.riztech.sehatq.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SehatqDatabase  : RoomDatabase(){
    abstract fun productDAO() : ProductDAO
}