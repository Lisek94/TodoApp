package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodosRepository
import kotlinx.coroutines.*

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val _viewState = MutableLiveData(
        Todo(
            name = "",
            date = "",
            category = ""
        )
    )

    private var todosRepository: TodosRepository = TodosRepository(application)
    private var allTodos: Deferred<LiveData<List<Todo>>> = todosRepository.getAllTodosAsync()

    private fun checkTodo(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun getAllTodos(): LiveData<List<Todo>> = runBlocking {
        allTodos.await()
    }

    fun onSaveButtonClicked(): Boolean {
        val todo = _viewState.value!!
        val canSave = checkTodo(todo.name)
        return if (canSave) {
            todosRepository.insertTodo(todo)
            true
        } else {
            false
        }
    }

    fun onNameTodoChanged(name: String) {
        _viewState.value = _viewState.value?.copy(name = name)
    }

    fun onDateTodoChanged(date: String) {
        _viewState.value = _viewState.value?.copy(date = date)
    }

    fun onCategoryTodoChanged(category: String) {
        _viewState.value = _viewState.value?.copy(category = category)
    }
}