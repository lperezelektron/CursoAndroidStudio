package com.example.androidexercises.ToDoApp

sealed class TaskCategory {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}