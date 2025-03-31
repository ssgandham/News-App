package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.manager.LocalUserManagerImpl
import com.example.newsapp.domain.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalUserManager(
        @ApplicationContext context: Context
    ): LocalUserManager {
        return LocalUserManagerImpl(context)
    }
}