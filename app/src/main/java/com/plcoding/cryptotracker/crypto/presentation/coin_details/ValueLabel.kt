package com.plcoding.cryptotracker.crypto.presentation.coin_details

import android.icu.number.NumberFormatter
import java.text.NumberFormat
import java.util.Locale

data class ValueLabel(
    val value: Float,
    val unit: String
){
    fun formatted():String{
        val formatter=NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val fractionDigits=when{
                value>1000 -> 0
                value in 2f .. 999f-> 2
                else->3
            }
            maximumFractionDigits=fractionDigits
            minimumIntegerDigits=0

        }
        return "${formatter.format(value)} $unit"

    }
}
