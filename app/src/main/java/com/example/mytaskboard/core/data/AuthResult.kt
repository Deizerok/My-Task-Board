package com.example.mytaskboard.core.data

interface AuthResult {

    fun map(mapper: Mapper)

    interface Mapper {

        fun mapSuccess()

        fun mapError(message: String)
    }

    object Success : AuthResult {

        override fun map(mapper: Mapper) = mapper.mapSuccess()
    }

    data class Error(private val message: String) : AuthResult {

        override fun map(mapper: Mapper) = mapper.mapError(message)
    }
}