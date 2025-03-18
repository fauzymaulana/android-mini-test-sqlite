package com.papero.minisqlite.core.networking

import com.papero.minisqlite.BuildConfig
import com.papero.minisqlite.core.libraries.C
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RequestClient {
    private fun getHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val reqBuilder = request.newBuilder()
                reqBuilder.header("Accept", C.HEADER_APP_JSON)
                    .header("Content-Type", C.HEADER_APP_JSON)

                val response = chain.proceed(reqBuilder.build())
                return@addInterceptor response.newBuilder().build()
            }

            .addInterceptor(interceptor)
            .connectTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    fun getClient(baseUrl: String = BuildConfig.BASE_URL): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getHttpClient())
            .build()
    }
}