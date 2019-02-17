package ru.cityproblemsmap.api

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.SendPointData
import ru.cityproblemsmap.api.model.UploadImageResponse

interface ApiService {

    @POST("/api/points/")
    fun sendPoints(@Body sendPointData: SendPointData): Observable<Any>

    @GET("/api/allpoints/")
    fun getAllPoints(): Observable<GetPointsResponse>

    //todo maybe add headers
    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @PUT("/api/uploadimage/")
    fun uploadImage(@Part("file") file: RequestBody): Observable<UploadImageResponse>

}