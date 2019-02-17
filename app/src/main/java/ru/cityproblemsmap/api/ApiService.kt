package ru.cityproblemsmap.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.SendPointData

interface ApiService {

    @POST("api/points/")
    fun sendPoints(@Body sendPointData: SendPointData): Observable<Any>

    @GET("/api/allpoints/")
    fun getAllPoints(): Observable<GetPointsResponse>

}