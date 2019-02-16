package ru.cityproblemsmap.api

import io.reactivex.Observable

interface ApiClient {

    fun sendPoint(lat: Double, lon: Double, title: String, description: String): Observable<Any>

}