package com.example.resepapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resepapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recipeList = ArrayList<Recipe>()
    private lateinit var adapter: RecipeAdapter

    companion object {
        const val ADD_RECIPE = 1
        const val EDIT_RECIPE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecipeAdapter(recipeList,
            onEdit = { position -> editRecipe(position) },
            onDelete = { position ->
                recipeList.removeAt(position)
                adapter.notifyItemRemoved(position)
            })

        binding.rvRecipes.layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivityForResult(intent, ADD_RECIPE)
        }
    }

    private fun editRecipe(position: Int) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("recipe", recipeList[position])
        intent.putExtra("position", position)
        startActivityForResult(intent, EDIT_RECIPE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val recipe = data.getSerializableExtra("recipe") as Recipe
            when (requestCode) {
                ADD_RECIPE -> {
                    recipeList.add(recipe)
                    adapter.notifyItemInserted(recipeList.size - 1)
                }
                EDIT_RECIPE -> {
                    val position = data.getIntExtra("position", -1)
                    if (position != -1) {
                        recipeList[position] = recipe
                        adapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }
}
