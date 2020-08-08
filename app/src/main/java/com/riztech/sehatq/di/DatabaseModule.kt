package com.riztech.sehatq.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.riztech.sehatq.database.ProductDAO
import com.riztech.sehatq.database.SehatqDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): SehatqDatabase{
        return Room.databaseBuilder(context, SehatqDatabase::class.java, "sehatq.db").build()
    }

    @Provides
    @Singleton
    fun provideProductsDAO(sehatqDatabase: SehatqDatabase): ProductDAO = sehatqDatabase.productDAO()

}