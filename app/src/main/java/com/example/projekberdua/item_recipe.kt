package com.example.resepapp

import java.io.Serializable

data class ItemRecipe(
    var title: String,
    var description: String
) : Serializable
