package com.riztech.sehatq.model

import java.io.Serializable

data class Product (
    val id: Int? = 0,
    val imageUrl: String? = "default",
    val title: String? = "default",
    val price: String? = "default",
    val description: String? = "default",
    val loved: Int? = 0
): Serializable