package com.example.mytaskboard.core.domain

interface LoadResult<T : Any> {

    fun map(mapper: Mapper<T>)

    interface Mapper<T : Any> {

        fun mapSuccess(items: List<T>)

        fun mapError(message: String)

    }

    data class Success<T : Any>(private val items: List<T>) : LoadResult<T> {

        override fun map(mapper: Mapper<T>) {
            mapper.mapSuccess(items)
        }
    }

    data class Error<T : Any>(private val message: String) : LoadResult<T> {

        override fun map(mapper: Mapper<T>) {
            mapper.mapError(message)
        }
    }
}