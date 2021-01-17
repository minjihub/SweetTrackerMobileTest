package com.example.sweettrackermobiletest.tracking

import android.util.Log
import com.example.sweettrackermobiletest.adapter.TrackingDetailAdapter
import com.example.sweettrackermobiletest.model.NewTrackingDetailData
import com.example.sweettrackermobiletest.model.TrackingData
import com.example.sweettrackermobiletest.utils.RetrofitUrlConnect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model(val requiredPresenter: Contract.RequiredPresenter) {
    var trackingData: TrackingData? = null

    fun getTrackingData(){
        val trackingDataService = RetrofitUrlConnect().trackingDataService
        trackingDataService?.getTrackingData()?.enqueue(object : Callback<TrackingData> {
            override fun onFailure(call: Call<TrackingData>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<TrackingData>, response: Response<TrackingData>) {
                val result = response.body()
                if(result != null){
                    trackingData = result
                    requiredPresenter.setTrackingData(trackingData)
                }
            }
        })
    }

    fun getAdapter(){
        val list = trackingData?.trackingDetail?.sortedWith(compareByDescending {
            it.time
        })

        val group = list?.groupBy {
            val splitArr = it.time?.split(" ")
            splitArr!![0]
        }

        val trackingArr = ArrayList<NewTrackingDetailData>()
        group?.forEach {
            var isCheck = true
            it.value.forEach { it ->
                if(isCheck){
                    val newDetail = NewTrackingDetailData(isCheck, trackingData?.purchaseItemDate, it)
                    trackingArr.add(newDetail)
                    isCheck = false
                }else{
                    val newDetail = NewTrackingDetailData(isCheck, trackingData?.purchaseItemDate, it)
                    trackingArr.add(newDetail)
                }
            }
        }

        val adapter = TrackingDetailAdapter(trackingArr)
        requiredPresenter.setAdapter(adapter)
    }
}