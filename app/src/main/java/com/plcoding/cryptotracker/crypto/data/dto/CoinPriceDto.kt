package com.plcoding.cryptotracker.crypto.data.dto

import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class CoinPriceDto(
    val priceUsd:Double,
    val time: Long

)
