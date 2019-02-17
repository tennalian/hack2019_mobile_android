package ru.cityproblemsmap.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.SendPointData

class ApiClientImpl(private val apiService: ApiService) : ApiClient {

    override fun sendPoint(lat: Double, lon: Double, title: String, description: String): Observable<Any> {
        return apiService.sendPoints(SendPointData(lat, lon, "1", "1", title))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllPoints(): Observable<GetPointsResponse> {
        return apiService.getAllPoints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}