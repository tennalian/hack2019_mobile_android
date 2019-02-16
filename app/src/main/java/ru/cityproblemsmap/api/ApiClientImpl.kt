package ru.cityproblemsmap.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.cityproblemsmap.api.model.Point

class ApiClientImpl(private val apiService: ApiService) : ApiClient {

    override fun sendPoint(lat: Double, lon: Double, title: String, description: String): Observable<Any> {
        return apiService.sendPoints(Point(lat, lon, "1", "1", title))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}