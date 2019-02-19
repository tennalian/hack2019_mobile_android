package ru.cityproblemsmap.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.SendPointData
import ru.cityproblemsmap.api.model.UploadImageResponse
import java.io.File


class ApiClientImpl(private val apiService: ApiService) : ApiClient {

    override fun sendPoint(lat: Double, lon: Double, title: String, description: String, photoUrl: String): Observable<Any> {
        return apiService.sendPoints(SendPointData(lat, lon, "4", "4", "1", title, photoUrl))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllPoints(): Observable<GetPointsResponse> {
        return apiService.getAllPoints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun uploadImage(file: File): Observable<UploadImageResponse> {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        val reqbody = RequestBody.create(MediaType.parse("image/*"), file)

        return apiService.uploadImage(reqbody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}