package ru.cityproblemsmap.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/points")
    fun sendPoints(@Body point: Point): Observable<Any>

}