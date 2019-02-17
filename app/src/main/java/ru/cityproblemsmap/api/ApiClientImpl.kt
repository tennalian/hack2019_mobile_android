package ru.cityproblemsmap.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.api.model.SendPointData
import ru.cityproblemsmap.api.model.UploadImageResponse
import java.io.File


class ApiClientImpl(private val apiService: ApiService) : ApiClient {

    override fun sendPoint(lat: Double, lon: Double, title: String, description: String): Observable<Any> {
        return apiService.sendPoints(SendPointData(lat, lon, "3", "3", "1", title))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllPoints(): Observable<GetPointsResponse> {
        return apiService.getAllPoints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun uploadImage(file: File): Observable<UploadImageResponse> {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // Используем FileUtils чтобы получить файл из uri

//        val file = FileUtils.getFile(this, fileUri)

        // Создаем RequestBody
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        // MultipartBody.Part используется, чтобы передать имя файла
        val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)
        val reqbody = RequestBody.create(MediaType.parse("multipart/form-data"), file)

//        // Добавляем описание
//        val descriptionString = "hello, this is description speaking"
//        val description = RequestBody.create(
//                MediaType.parse("multipart/form-data"), descriptionString)

        return apiService.uploadImage(reqbody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}