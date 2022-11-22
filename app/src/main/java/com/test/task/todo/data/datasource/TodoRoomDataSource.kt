package com.test.task.todo.data.datasource

import com.test.task.todo.data.dao.TodoDao
import com.test.task.todo.data.model.Resource
import com.test.task.todo.data.model.Todos
import javax.inject.Inject

class TodoRoomDataSource @Inject constructor(
    private val dao: TodoDao
    ) {

    suspend fun getTodos(): Resource<List<Todos>> {
        return Resource.Success(dao.getAllTodos())
    }

    suspend fun addTodo(todo: Todos){
        return dao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todos){
        dao.update(todo)
    }

    suspend fun addAllTodos(todos: List<Todos>){
        return dao.insertAllTodos(todos)
    }
}