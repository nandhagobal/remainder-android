package com.tw.remainder.mapper

import com.tw.remainder.database.entities.Task
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.entities.TaskStatus
import com.tw.remainder.utils.Mapper

class TaskResponseMapper:Mapper<Task,TaskEntity> {
    override fun from(e: Task): TaskEntity {
        return TaskEntity(id = e.id, title = e.name, date = e.date, time = e.time, status = TaskStatus.valueOf(e.status))
    }
}