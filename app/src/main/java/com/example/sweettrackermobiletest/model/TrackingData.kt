package com.example.sweettrackermobiletest.model

data class TrackingData(val parcelCompanyCode: String?,
                        val parcelCompanyName: String?,
                        val parcelInvoice: String?,
                        val parcelLevel: Int?,
                        val parcelDeliverTime: String?,
                        val purchaseItemImg: String?,
                        val purchaseItemName: String?,
                        val purchaseItemDate: String?,
                        val trackingDetail: ArrayList<TrackingDetailData>?)