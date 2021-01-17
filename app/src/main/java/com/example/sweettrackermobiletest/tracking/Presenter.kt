package com.example.sweettrackermobiletest.tracking

import com.example.sweettrackermobiletest.adapter.TrackingDetailAdapter
import com.example.sweettrackermobiletest.model.TrackingData

class Presenter(val view: Contract.View?) : Contract.Presenter, Contract.RequiredPresenter {
    var model: Model? = null

    init {
        model = Model(this)
    }

    override fun getTrackingData() {
        model?.getTrackingData()
    }

    override fun setTrackingData(trackingData: TrackingData?) {
        view?.setTrackingData(trackingData)
    }

    override fun getAdapter() {
        model?.getAdapter()
    }

    override fun setAdapter(adapter: TrackingDetailAdapter) {
        view?.setAdapter(adapter)
    }
}