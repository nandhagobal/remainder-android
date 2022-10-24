package com.tw.remainder.useCase

import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.repositories.TaskRepository

class GetAllTaskUseCase(private val repository: TaskRepository) {
    suspend fun invoke(): List<TaskEntity> {
        return repository.getAllTask()
    }
}