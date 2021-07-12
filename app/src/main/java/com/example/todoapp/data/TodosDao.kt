package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodosDao {

    @Insert
    fun insert(todo: Todo)

    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): LiveData<List<Todo>>
}