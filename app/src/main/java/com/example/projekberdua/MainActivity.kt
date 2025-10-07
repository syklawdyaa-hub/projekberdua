package com.example.projekberdua

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    private lateinit var rvRecipes: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeList = ArrayList<ItemRecipe>() // <- FIXED HERE

    companion object {
        const val ADD_RECIPE = 100
        const val EDIT_RECIPE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRecipes = findViewById(R.id.rvRecipes)
        btnAdd = findViewById(R.id.tomtam)

        recipeAdapter = RecipeAdapter(recipeList,
            onEdit = { position -> editRecipe(position) },
            onDelete = { position ->
                recipeList.removeAt(position)
                recipeAdapter.notifyItemRemoved(position)
            }
        )

        rvRecipes.layoutManager = LinearLayoutManager(this)
        rvRecipes.adapter = recipeAdapter

        btnAdd.setOnClickListener {
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
            val recipe = data.getSerializableExtra("recipe") as ItemRecipe
            when (requestCode) {
                ADD_RECIPE -> {
                    recipeList.add(recipe)
                    recipeAdapter.notifyItemInserted(recipeList.size - 1)
                }
                EDIT_RECIPE -> {
                    val position = data.getIntExtra("position", -1)
                    if (position != -1) {
                        recipeList[position] = recipe
                        recipeAdapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }
}
