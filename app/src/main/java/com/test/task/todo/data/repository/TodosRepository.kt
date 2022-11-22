package com.test.task.todo.data.repository

import com.test.task.todo.data.model.Resource
import com.test.task.todo.data.model.Todos
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface TodosRepository {
    suspend fun getTodosFromNetwork(): Resource<List<Todos>>
    suspend fun getTodosFromRoom(): Resource<List<Todos>>
    suspend fun addTodo(todo: Todos)
    suspend fun updateTodo(todo: Todos)
    suspend fun addAllTodos(todos: List<Todos>)
}