package com.tw.remainder.useCase

import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.repositories.TaskRepository

class SaveTaskUseCase(val repository: TaskRepository) {
    suspend fun invoke(task:TaskEntity){
        repository.saveTask(task)
    }
}