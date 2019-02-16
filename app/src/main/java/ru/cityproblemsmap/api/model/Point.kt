package ru.cityproblemsmap.api.model

data class Point(
    val latitude: Double,
    val longitude: Double,
    val group: String,
    val category: String,
    val description: String,
    val photoUrl: String? = ""
)