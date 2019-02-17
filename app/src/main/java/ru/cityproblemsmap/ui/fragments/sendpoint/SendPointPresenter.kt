package ru.cityproblemsmap.ui.fragments.sendpoint

import android.content.Context
import android.net.Uri
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.cityproblemsmap.api.ApiClient
import ru.cityproblemsmap.ui.base.BasePresenter
import ru.cityproblemsmap.utils.FileUtil

@InjectViewState
class SendPointPresenter : BasePresenter<SendPointView>(), KoinComponent {

    companion object {
        const val TAG = "SendPointPresenter"
    }

    private val apiClient: ApiClient by inject()

    private val context: Context by inject()

    fun onSendButtonPressed(title: String, description: String, imageUri: Uri?) {
        viewState.setButtonEnabled(false)

        val file = FileUtil.from(context, imageUri!!)
        disposables.add(
                apiClient.uploadImage(file)
                        .subscribe({
                            onPhotoUploaded(title, description, it.url)
                            Log.w(TAG, "Got uploadimageresponse")
                        }) {
                            viewState.setButtonEnabled(true)
                            viewState.showToast("Ошибка загрузки фото")
                            Log.e(TAG, "Error uploading image", it)
                        }
        )


    }

    fun onPhotoAdded(photoUri: Uri) {
        viewState.showPhoto(photoUri)
    }

//    fun uploadPhoto(imageUri: Uri?) {
////        val file = FileUtils.getFile(context, imageUri)
//
////        val file = File("${imageUri?.path}")
////        val inputStream = context.contentResolver.openInputStream(imageUri) ?: return
////        val out = FileOutputStream(file)
////        val buf = ByteArray(1024)
////        var len: Int = inputStream.read(buf)
////        while (len > 0) {
////            out.write(buf, 0, len)
////            len = inputStream.read(buf)
////        }
////        out.close()
////        inputStream.close()
//
//        val file = FileUtil.from(context, imageUri!!)
//
//        if (file == null) {
//            Log.e(TAG, "file is null")
//            return
//        }
//
//        disposables.add(
//                apiClient.uploadImage(file)
//                        .subscribe({
//                            Log.w(TAG, "Got uploadimageresponse")
//                        }) {
//                            Log.e(TAG, "Error uploading image", it)
//                        }
//        )
//    }

    private fun onPhotoUploaded(title: String, description: String, imageUrl: String) {
        disposables.add(
                apiClient.sendPoint(54.1, 20.1, title, description, imageUrl)
                        .subscribe({
                            Log.v("ApiClientImpl", "Got info: ${it::class.java}")
                            popFragment()
                        }) {
                            Log.e("ApiClientImpl", "Error", it)

                            viewState.showToast("Ошибка отправки фото")
                            viewState.setButtonEnabled(true)
                        }
        )
    }

    private fun popFragment() {
        viewState.showToast("Обращение успешно отправлено!")
        viewState.popFragment()
    }

}
