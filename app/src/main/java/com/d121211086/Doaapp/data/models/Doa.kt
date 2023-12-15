package com.d121211086.Doaapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Doa(
    @SerialName("id")
    val id: String?,
    @SerialName("doa")
    val doa: String?,
    @SerialName("ayat")
    val ayat: String?,
    @SerialName("latin")
    val latin: String?,
    @SerialName("artinya")
    val artinya: String?
) : Parcelable