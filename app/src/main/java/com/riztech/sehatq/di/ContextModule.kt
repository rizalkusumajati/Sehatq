package com.riztech.sehatq.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ContextModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

}