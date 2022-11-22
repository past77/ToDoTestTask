package com.test.task.todo.data.api

import com.test.task.todo.data.model.Todos
import retrofit2.http.GET
import retrofit2.http.Query

interface TodosApi {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("todos")
    suspend fun getTodos(
        @Query("userId")
        userid: Int = 1,
    ): List<Todos>
}