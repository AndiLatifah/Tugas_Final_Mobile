package com.d121211086.Doaapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.d121211086.Doaapp.data.repository.DoaRepository
import com.d121211086.Doaapp.data.source.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val doaRepository: DoaRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://doa-doa-api-ahmadramadhan.fly.dev"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val doaRepository: DoaRepository
        get() = DoaRepository(retrofitService)

}