package ru.cityproblemsmap.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import ru.cityproblemsmap.api.model.PointData

interface ApiService {

    @POST("api/points/")
    fun sendPoints(@Body pointData: PointData): Observable<Any>

}