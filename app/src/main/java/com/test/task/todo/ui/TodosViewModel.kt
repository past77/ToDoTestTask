package com.test.task.todo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.test.task.todo.data.model.Resource
import com.test.task.todo.data.model.Todos
import com.test.task.todo.data.repository.TodosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todosRepository: TodosRepository,
) : ViewModel() {

    suspend fun getTodosFromApi() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(todosRepository.getTodosFromNetwork())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    suspend fun getTodosFromDB() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(todosRepository.getTodosFromRoom())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun addAllTodos(todos: List<Todos>) {
        viewModelScope.launch {
            todosRepository.addAllTodos(todos)
        }
    }

    fun updateTodo(todo: Todos) {
        viewModelScope.launch {
            todosRepository.updateTodo(todo)
        }
    }

    suspend fun addTodo(todo: Todos) {
        todosRepository.addTodo(todo)
    }

}
