package com.tw.remainder.repositories

import com.tw.remainder.entities.TaskEntity

interface TaskRepository {
    suspend fun getAllTask():List<TaskEntity>
    suspend fun saveTask(task:TaskEntity)
    suspend fun getTask(id: String): TaskEntity
}
