package com.example.newsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.LocalUserManager
import com.example.newsapp.ui.uiStates.OnboardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val localUserManager: LocalUserManager
): ViewModel() {

suspend fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.SaveAppEntry -> saveAppEntry()
        }
    }

    suspend fun  saveAppEntry() {
        localUserManager.saveAppEntry()
        readAppEntry()
    }

    suspend fun readAppEntry(): Flow<Boolean> {
        val result = localUserManager.readAppEntry()

        result.collect { value ->
            Timber.tag("Result Check the alue").i(value.toString())
            Log.i("Result Check the alue", value.toString())
        }

        return result
    }

}