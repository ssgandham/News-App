package com.example.newsapp.domain

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    suspend fun readAppEntry(): Flow<Boolean>
}