package com.tw.remainder.useCase

import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.repositories.TaskRepository

class GetTaskUseCase(private val repository: TaskRepository) {
    suspend fun invoke(id: String): TaskEntity {
        return repository.getTask(id)
    }
}