package com.example.resepapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.projekberdua.R

class AddEditActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button

    private var position: Int = -1
    private var isEditMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        btnSave = findViewById(R.id.btnSave)

        val recipe = intent.getSerializableExtra("recipe") as? ItemRecipe
        position = intent.getIntExtra("position", -1)

        if (recipe != null) {
            isEditMode = true
            etTitle.setText(recipe.title)
            etDescription.setText(recipe.description)
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("recipe", ItemRecipe(title, description))
            if (isEditMode) resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
