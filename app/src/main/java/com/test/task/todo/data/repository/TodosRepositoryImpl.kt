package com.test.task.todo.data.repository

import com.test.task.todo.data.api.TodosApi
import com.test.task.todo.data.datasource.TodoNetworkDataSource
import com.test.task.todo.data.datasource.TodoRoomDataSource
import com.test.task.todo.data.model.Todos
import javax.inject.Inject

class TodosRepositoryImpl @Inject constructor(
    private val networkDataSource: TodoNetworkDataSource,
    private val roomDataSource: TodoRoomDataSource

) : TodosRepository {

    @Inject
    lateinit var api: TodosApi

    override suspend fun getTodosFromNetwork() = networkDataSource.getTodos()

    override suspend fun getTodosFromRoom() = roomDataSource.getTodos()

    override suspend fun addTodo(todo: Todos) = roomDataSource.addTodo(todo)

    override suspend fun updateTodo(todo: Todos) = roomDataSource.updateTodo(todo)

    override suspend fun addAllTodos(todos: List<Todos>) = roomDataSource.addAllTodos(todos)

}