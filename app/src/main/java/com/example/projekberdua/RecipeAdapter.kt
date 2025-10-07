package com.example.resepapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resepapp.databinding.ItemRecipeBinding

class RecipeAdapter(
    private val recipes: ArrayList<Recipe>,
    private val onEdit: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.tvTitle.text = recipe.title
        holder.binding.tvDescription.text = recipe.description

        holder.binding.btnEdit.setOnClickListener { onEdit(position) }
        holder.binding.btnDelete.setOnClickListener { onDelete(position) }
    }
}
