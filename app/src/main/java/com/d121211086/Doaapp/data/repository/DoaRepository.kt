package com.d121211086.Doaapp.data.repository

import com.d121211086.Doaapp.data.response.GetDoaResponse
import com.d121211086.Doaapp.data.source.remote.ApiService

class DoaRepository(private val apiService: ApiService) {

    suspend fun getDoa(): GetDoaResponse {
        return apiService.getDoa()
    }

}