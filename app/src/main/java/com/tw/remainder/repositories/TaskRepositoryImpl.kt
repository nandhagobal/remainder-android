package com.tw.remainder.repositories

import com.tw.remainder.database.TaskDAO
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.mapper.TaskRequestMapper
import com.tw.remainder.mapper.TaskResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class TaskRepositoryImpl(private val taskDAO: TaskDAO) : TaskRepository {
    override suspend fun getAllTask(): List<TaskEntity> {
        return withContext(Dispatchers.IO) {
            val taskResponse = taskDAO.getAllTask()
            taskResponse.map { TaskResponseMapper().from(it) }
        }
    }

    override suspend fun saveTask(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDAO.addTask(TaskRequestMapper().from(task))
        }
    }

    override suspend fun getTask(id: String): TaskEntity {
        return withContext(Dispatchers.IO){
            TaskResponseMapper().from(taskDAO.getTask(id))
        }
    }
}