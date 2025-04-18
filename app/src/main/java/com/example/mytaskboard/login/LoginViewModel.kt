package com.example.mytaskboard.login

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fakestore.core.presentation.ProvideLiveData
import com.example.mytaskboard.core.data.AuthRepository
import com.example.mytaskboard.core.presentation.BaseViewModel
import com.example.mytaskboard.core.presentation.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val liveDataWrapper: LoginLiveDataWrapper,
    private val mapper: LoginResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<LoginUiState> {

    fun loginWithGoogle(context: Context) {
        liveDataWrapper.updateUi(LoginUiState.Progress)
        runAsync({
            authRepository.loginWithGoogle(context)
        }) { authResult ->
            authResult.map(mapper)
        }
    }

    override fun liveData(): LiveData<LoginUiState> = liveDataWrapper.liveData()
}