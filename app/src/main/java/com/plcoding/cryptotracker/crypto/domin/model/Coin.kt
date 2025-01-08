package com.plcoding.cryptotracker.crypto.domin.model

data class Coin(
    val id:String,
    val rank:Int,
    val name:String,
    val symbol:String,
    val marketCapUsd:Double,
    val priceUsd:Double,
    val changePercentage24Hr:Double
)
