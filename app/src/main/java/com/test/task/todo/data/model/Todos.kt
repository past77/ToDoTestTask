package com.test.task.todo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "todo_table")
data class Todos(
    val userId: Int = 1,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    var completed: Boolean
): Parcelable