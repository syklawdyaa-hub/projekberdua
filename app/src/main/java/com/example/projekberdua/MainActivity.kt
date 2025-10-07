package com.example.bukuresep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recipes = listOf(
        Recipe("Nasi Goreng", "Nasi, bawang merah, bawang putih, kecap", "1. Tumis bawang\n2. Masukkan nasi\n3. Tambahkan kecap\n4. Aduk rata"),
        Recipe("Mie Goreng", "Mie telur, sawi, kecap", "1. Rebus mie\n2. Tumis sayur\n3. Campurkan mie dan bumbu"),
        Recipe("Ayam Bakar", "Ayam, bumbu marinasi", "1. Marinasi ayam\n2. Bakar hingga matang\n3. Sajikan hangat")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = RecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", recipe.title)
            intent.putExtra("ingredients", recipe.ingredients)
            intent.putExtra("steps", recipe.steps)
            startActivity(intent)
        }
    }
}
