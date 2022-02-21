package co.trance.lib.utility.helper

import android.util.Log
import co.trance.lib.utility.guide.repository.service.IAPIService
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIRetrofitServiceManager<T: IAPIService>() {
    companion object Shared {
        fun <T : IAPIService> instance(): APIRetrofitServiceManager<T> {
            return APIRetrofitServiceManager()
        }
    }

    fun create(service: Class<T>, httpUrl: HttpUrl?): T {
        val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        })

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//        if (BuildConfig.DEBUG) {
            logger.apply { logger.level = HttpLoggingInterceptor.Level.BODY }
            client.addInterceptor(logger)
//        }
        return Retrofit.Builder()
            .baseUrl(httpUrl!!)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(service)
    }
}