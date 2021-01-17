package com.example.sweettrackermobiletest

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweettrackermobiletest.adapter.TrackingDetailAdapter
import com.example.sweettrackermobiletest.databinding.ActivityTrackingBinding
import com.example.sweettrackermobiletest.model.TrackingData
import com.example.sweettrackermobiletest.tracking.Contract
import com.example.sweettrackermobiletest.tracking.Presenter

class TrackingActivity : AppCompatActivity(), Contract.View {
    lateinit var binding: ActivityTrackingBinding
    private var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tracking)
        presenter = Presenter(this)
        presenter?.getTrackingData()

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setTrackingData(trackingData: TrackingData?) {
        trackingData?.apply {
            binding.itemName.text = this.purchaseItemName
            binding.invoice.text = this.parcelInvoice
            binding.parcelCompany.text = this.parcelCompanyName
            binding.statusSub.text = "도착예정시간 : ${this.parcelDeliverTime}"
            binding.date.text = "등록일 : ${this.purchaseItemDate}"

            Glide
                .with(this@TrackingActivity)
                .load(this.purchaseItemImg)
                .into(binding.itemImg)

            var status = "운송장 없음"
            var progressValue = 0
            var pinValue = 0

            when(this.parcelLevel){
                0 -> {
                    status = "집하"
                }
                1 -> {
                    status = "배송중"
                    progressValue = 33
                    pinValue = 84
                }
                2 -> {
                    status = "배달출발"
                    progressValue = 66
                    pinValue = 168
                }
                3 -> {
                    status = "배달완료"
                    progressValue = 100
                    pinValue = 252
                }
            }
            binding.status.text = status
            presenter?.getAdapter()

            val progressAni = ObjectAnimator.ofInt(binding.progressBar, "progress", progressValue)
            progressAni.duration = 3000
            progressAni.start()

            val pixel = pinValue * resources.displayMetrics.density
            val pinMoveAni = ObjectAnimator.ofFloat(binding.pinImg,"translationX", pixel)
            pinMoveAni.duration = 3000
            pinMoveAni.start()
        }
    }

    override fun setAdapter(adapter: TrackingDetailAdapter) {
        val layoutManager = LinearLayoutManager(this)

        binding.detailList.let {
            it.adapter = adapter
            it.layoutManager = layoutManager as RecyclerView.LayoutManager?
            it.isNestedScrollingEnabled = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

        }
        return super.onOptionsItemSelected(item)
    }
}
