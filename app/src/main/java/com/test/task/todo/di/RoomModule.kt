package com.test.task.todo.di

import android.content.Context
import androidx.room.Room
import com.test.task.todo.data.dao.TodoDao
import com.test.task.todo.data.database.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): TodosDatabase {
        return Room.databaseBuilder(
            appContext,
            TodosDatabase::class.java,
            "todos-db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesProductDao(imageDatabase: TodosDatabase): TodoDao {
        return imageDatabase.todoDao()
    }

}