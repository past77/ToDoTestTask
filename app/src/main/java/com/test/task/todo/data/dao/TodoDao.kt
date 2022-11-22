package com.test.task.todo.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.test.task.todo.data.model.Todos


@Dao
interface TodoDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertTodo(todo: Todos)

    @Insert(onConflict = REPLACE)
    suspend fun insertAllTodos(todos: List<Todos>)

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    suspend fun getAllTodos(): List<Todos>

    @Query("DELETE FROM todo_table")
    suspend fun clearTable()

    @Update
    suspend fun update(todo: Todos)

    @Delete
    suspend fun deleteTodo(todo: Todos)
}