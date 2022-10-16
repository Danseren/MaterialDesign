package com.geekbrains.material.viewmodel

import com.geekbrains.material.ui.main.model.EarthEpicServerResponseData
import com.geekbrains.material.ui.main.model.MarsPhotosServerResponseData
import com.geekbrains.material.ui.main.model.PODServerResponseData

sealed class AppState {
    data class SuccessPOD(val serverResponseData: PODServerResponseData) : AppState()
    data class SuccessEarthEpic (val serverResponseData: List<EarthEpicServerResponseData>) : AppState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}