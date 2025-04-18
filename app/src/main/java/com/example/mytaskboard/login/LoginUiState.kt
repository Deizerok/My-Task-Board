package com.example.mytaskboard.login

import android.widget.Toast
import com.example.mytaskboard.databinding.FragmentLoginBinding

interface LoginUiState {

    fun show(binding: FragmentLoginBinding)

    object Progress : LoginUiState {

        override fun show(binding: FragmentLoginBinding) = with(binding) {
            loginButton.isEnabled = false
        }
    }

    data class Error(private val message: String) : LoginUiState {

        override fun show(binding: FragmentLoginBinding) = with(binding) {
            loginButton.isEnabled = true
            Toast.makeText(
                loginButton.context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}