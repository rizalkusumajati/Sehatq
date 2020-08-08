package com.riztech.sehatq.di

import com.riztech.sehatq.api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiModule {
    private val BASE_URL = "https://private-4639ce-ecommerce56.apiary-mock.com/"

    @Provides
    @Singleton
    fun provideHomeApi(): HomeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }

}