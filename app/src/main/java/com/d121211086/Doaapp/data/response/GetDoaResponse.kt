package com.d121211086.Doaapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDoaResponse(
    @SerialName("doa")
    val doa: String?,
    @SerialName("ayat")
    val ayat: String?,
    @SerialName("latin")
    val latin: String?,
    @SerialName("artinya")
    val artinya: String?
)