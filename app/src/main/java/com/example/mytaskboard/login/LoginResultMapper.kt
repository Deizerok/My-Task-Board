package com.example.mytaskboard.login

import com.example.mytaskboard.core.data.AuthResult
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.taskboard.board.TaskBoardScreen
import javax.inject.Inject

interface LoginResultMapper : AuthResult.Mapper {

    class Base @Inject constructor(
        private val navigation: Navigation.Navigate,
        private val liveDataWrapper: LoginLiveDataWrapper
    ) : LoginResultMapper {

        override fun mapSuccess() {
            navigation.updateUi(TaskBoardScreen)
        }

        override fun mapError(message: String) {
            liveDataWrapper.updateUi(LoginUiState.Error(message = message))
        }
    }
}