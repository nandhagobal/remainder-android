package com.tw.remainder.mapper

import com.tw.remainder.database.entities.Task
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.utils.Mapper

class TaskRequestMapper: Mapper<TaskEntity, Task> {
    override fun from(e: TaskEntity): Task {
        return Task(name = e.name)
    }
}