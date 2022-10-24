package com.tw.remainder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tw.remainder.database.entities.Task

@Dao
interface TaskDAO {
    @Query("select * from task")
    fun getAllTask(): List<Task>

    @Insert
    fun addTask(task:Task)
}