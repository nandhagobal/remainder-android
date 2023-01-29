package com.tw.remainder.mapper

import com.tw.remainder.database.entities.Task
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.utils.Mapper

class TaskRequestMapper: Mapper<TaskEntity, Task> {
    override fun from(e: TaskEntity): Task {
        return Task(id = e.id,name = e.title, date = e.date, time = e.time, status = e.status.value)
    }
}