package ru.cityproblemsmap.api

import io.reactivex.Observable
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.UploadImageResponse
import java.io.File

interface ApiClient {

    fun sendPoint(lat: Double, lon: Double, title: String, description: String, photoUrl: String): Observable<Any>

    fun getAllPoints(): Observable<GetPointsResponse>

    fun uploadImage(file: File): Observable<UploadImageResponse>

}