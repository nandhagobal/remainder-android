package com.tw.remainder.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tw.remainder.database.entities.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDAO():TaskDAO
}