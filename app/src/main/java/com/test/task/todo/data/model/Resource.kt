package com.test.task.todo.data.model

import java.lang.Exception

sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val e:Exception): Resource<T>()
}