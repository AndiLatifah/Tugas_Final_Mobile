package com.d121211086.Doaapp.data.source.remote

import com.d121211086.Doaapp.data.response.GetDoaResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/api")
    suspend fun getDoa(): GetDoaResponse

}