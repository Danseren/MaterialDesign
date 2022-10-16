package com.geekbrains.material.ui.main.model

import com.google.gson.annotations.SerializedName

data class MarsPhotosServerResponseData(
    @field:SerializedName("photos") val photos: ArrayList<MarsServerResponseData>,

    )
