package com.example.sweettrackermobiletest.utils

import com.example.sweettrackermobiletest.model.TrackingData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitUrlConnect {
    private var retrofit:Retrofit? = null
    var trackingDataService: TrackingDataService? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://img.sweettracker.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        trackingDataService = retrofit?.create(TrackingDataService::class.java)
    }

    interface TrackingDataService{
        @GET("image/mobile_test/mobile.json")
        fun getTrackingData(): Call<TrackingData>
    }

}