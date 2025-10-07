package com.example.bukuresep

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")
        val ingredients = intent.getStringExtra("ingredients")
        val steps = intent.getStringExtra("steps")

        findViewById<TextView>(R.id.titleDetail).text = title
        findViewById<TextView>(R.id.ingredientsDetail).text = ingredients
        findViewById<TextView>(R.id.stepsDetail).text = steps
    }
}
