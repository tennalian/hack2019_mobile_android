package ru.cityproblemsmap.api

interface ApiClient {

    fun sendPoint(lat: Double, lon: Double, title: String, description: String)

}