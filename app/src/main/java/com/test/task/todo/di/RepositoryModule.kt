package com.test.task.todo.di

import com.test.task.todo.data.repository.TodosRepository
import com.test.task.todo.data.repository.TodosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTodosRepository(todosRepository: TodosRepositoryImpl): TodosRepository

}
