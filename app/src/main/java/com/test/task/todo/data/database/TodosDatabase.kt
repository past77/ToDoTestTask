package com.test.task.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.task.todo.data.dao.TodoDao

import com.test.task.todo.data.model.Todos

@Database(entities = [Todos::class], version = 1)
abstract class TodosDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

}