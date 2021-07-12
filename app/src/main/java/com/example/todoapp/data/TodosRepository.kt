package com.example.todoapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*


class TodosRepository(application: Application)  {

    private var todosDao: TodosDao

    init {
        val database = TodosDatabase.getInstance(application)

        todosDao = database!!.todoDao()
    }

    fun insertTodo(todo: Todo) = CoroutineScope(Dispatchers.IO).launch {
        todosDao.insert(todo)
    }

    fun getAllTodosAsync(): Deferred<LiveData<List<Todo>>> =
        CoroutineScope(Dispatchers.IO).async {
        todosDao.getAllTodos()
    }

}