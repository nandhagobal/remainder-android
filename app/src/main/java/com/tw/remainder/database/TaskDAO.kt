package com.tw.remainder.database

import androidx.room.*
import com.tw.remainder.database.entities.Task

@Dao
interface TaskDAO {
    @Query("select * from task")
    fun getAllTask(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task:Task)

    @Query("select * from task where id=:id")
    fun getTask(id: String): Task
}