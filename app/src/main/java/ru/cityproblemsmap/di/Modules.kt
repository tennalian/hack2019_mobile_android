package ru.cityproblemsmap.di

import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.cityproblemsmap.api.ApiClient
import ru.cityproblemsmap.api.ApiClientImpl
import ru.cityproblemsmap.api.ApiService

val apiModule = module {

    //    single { androidContext() }

    single<ApiService> {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://178.128.91.191/")
                .build()

        retrofit.create(ApiService::class.java)
    }
    single<ApiClient> { ApiClientImpl(get()) }
}

val modules = listOf(apiModule)
