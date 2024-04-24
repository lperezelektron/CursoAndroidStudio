package com.example.androidexercises.ToDoApp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexercises.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)

    fun render(taskCategory: TaskCategory) {

    }

}