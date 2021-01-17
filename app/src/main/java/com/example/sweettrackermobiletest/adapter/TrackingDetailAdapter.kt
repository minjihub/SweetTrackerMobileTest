package com.example.sweettrackermobiletest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweettrackermobiletest.databinding.TrackingDetailItemBinding
import com.example.sweettrackermobiletest.model.NewTrackingDetailData
import java.text.SimpleDateFormat
import kotlin.math.abs

class TrackingDetailAdapter(private val data: ArrayList<NewTrackingDetailData>) : RecyclerView.Adapter<TrackingDetailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrackingDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(private val binding: TrackingDetailItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: NewTrackingDetailData){
            val dateFormat = SimpleDateFormat("yyyy-mm-dd")
            val purchaseDate = dateFormat.parse(item.purchaseDate)
            val detailDate = dateFormat.parse(item.detail?.time)
            var days = (purchaseDate.time - detailDate.time) / (24*60*60*1000)
            days = abs(days)

            binding.dDay.text = "D+$days"
            binding.status.text = item.detail?.status
            binding.location.text = item.detail?.where

            val dateArr = item.detail?.time?.split(" ")
            binding.date.text = dateArr?.get(0) ?: " "
            binding.time.text = dateArr?.get(1) ?: " "

            if(item.check){
                binding.dateLayout.visibility = View.VISIBLE
            }
        }
    }
}