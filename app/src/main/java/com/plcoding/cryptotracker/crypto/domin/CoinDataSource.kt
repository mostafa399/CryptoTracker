package com.plcoding.cryptotracker.crypto.domin

import com.plcoding.cryptotracker.core.domin.util.NetworkError
import com.plcoding.cryptotracker.core.domin.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>,NetworkError>
    suspend fun getCoinsHistory(
        coinId:String,
        start:ZonedDateTime,
        end: ZonedDateTime
    ):Result<List<CoinPrice>,NetworkError>
}