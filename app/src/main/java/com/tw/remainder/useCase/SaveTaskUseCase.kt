package com.tw.remainder.useCase

import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.repositories.TaskRepository

class SaveTaskUseCase(private val repository: TaskRepository) {
    suspend fun addTask(task:TaskEntity){
        repository.saveTask(task)
    }
}