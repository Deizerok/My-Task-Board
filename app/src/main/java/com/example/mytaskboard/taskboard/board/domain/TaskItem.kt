package com.example.mytaskboard.taskboard.board.domain

interface TaskItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun map(
            id: Int,
            title: String,
            description: String,
            time: Int,
            picture: ByteArray
        ): T
    }

    data class Base(
        private val id: Int,
        private val title: String,
        private val description: String,
        private val time: Int,
        private val picture: ByteArray
        ) : TaskItem {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(
            id, title, description, time, picture
        )
    }
}