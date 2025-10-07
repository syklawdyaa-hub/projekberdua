package com.example.resepapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.resepapp.databinding.ActivityAddEditBinding

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private var position: Int = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        position = intent.getIntExtra("position", -1)
        isEditMode = recipe != null

        if (isEditMode) {
            binding.etTitle.setText(recipe?.title)
            binding.et
