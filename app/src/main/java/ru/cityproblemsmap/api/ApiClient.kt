package ru.cityproblemsmap.api

import io.reactivex.Observable
import ru.cityproblemsmap.api.model.GetPointsResponse

interface ApiClient {

    fun sendPoint(lat: Double, lon: Double, title: String, description: String): Observable<Any>

    fun getAllPoints(): Observable<GetPointsResponse>

}