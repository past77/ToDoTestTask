package com.test.task.todo.data.datasource

import com.test.task.todo.data.api.TodosApi
import com.test.task.todo.data.model.Resource
import com.test.task.todo.data.model.Todos
import javax.inject.Inject

class TodoNetworkDataSource @Inject constructor(private val api: TodosApi) {

    suspend fun getTodos(): Resource<List<Todos>> {
        return Resource.Success(api.getTodos())
    }
}