package com.example.androidexercises.ToDoApp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexercises.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    private val cvContainerCategories: CardView = view.findViewById(R.id.cvContainerCategories)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if (taskCategory.isSelected){
            R.color.background_card
        }else{
            R.color.background_disabled_
        }

        cvContainerCategories.setCardBackgroundColor(
            ContextCompat.getColor(cvContainerCategories.context, color)
        )

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.business)
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.business_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.others)
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.personal)
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
        }
    }

}