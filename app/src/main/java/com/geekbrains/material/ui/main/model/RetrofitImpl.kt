package com.geekbrains.material.ui.main.model

import com.geekbrains.material.util.API_NASA
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(API_NASA)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(RetrofitAPI :: class.java)
    }

    // Picture Of the Day
    fun getPOD (apiKey: String, date: String, podCallback: Callback<PODServerResponseData>) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }

    // Earth Polychromatic Imaging Camera
    fun getEPIC (apiKey: String, epicCallback: Callback<List<EarthEpicServerResponseData>>) {
        api.getEPIC(apiKey).enqueue(epicCallback)
    }

    fun getMarsPictureByDate (eartDate: String, apiKey: String, marsCallbackByDate: Callback<MarsPhotosServerResponseData>) {
        api.getMarsImageByDate(eartDate, apiKey).enqueue(marsCallbackByDate)
    }
}