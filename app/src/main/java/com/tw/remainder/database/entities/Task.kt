package com.tw.remainder.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val name:String,
    val date:String,
    val time:String
    )
