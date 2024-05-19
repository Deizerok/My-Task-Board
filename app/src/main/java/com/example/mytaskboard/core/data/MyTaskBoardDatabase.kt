package com.example.mytaskboard.core.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false
)
abstract class MyTaskBoardDatabase : RoomDatabase() {}