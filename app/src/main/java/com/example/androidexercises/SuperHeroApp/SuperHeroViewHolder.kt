package com.example.androidexercises.SuperHeroApp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexercises.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superHeroItemResponse:SuperHeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = superHeroItemResponse.name
        Picasso.get().load(superHeroItemResponse.image.url).into(binding.ivHero)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.id) }
    }
}