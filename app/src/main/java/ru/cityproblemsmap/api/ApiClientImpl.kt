package ru.cityproblemsmap.api

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiClientImpl(private val apiService: ApiService) : ApiClient {

    override fun sendPoint(lat: Double, lon: Double, title: String, description: String) {
        val d = apiService.sendPoints(Point(lat, lon, "1", "1", title))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Log.v("ApiClientImpl", "Got info: ${it::class.java}")
            }) {
                Log.e("ApiClientImpl", "Error", it)
            }
    }

}