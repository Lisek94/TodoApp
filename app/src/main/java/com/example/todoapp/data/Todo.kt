package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(var name:String,
                var date: String?,
                var category: String) {

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}