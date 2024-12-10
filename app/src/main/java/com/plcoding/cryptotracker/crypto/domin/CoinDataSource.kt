package com.plcoding.cryptotracker.crypto.domin

import com.plcoding.cryptotracker.core.domin.util.NetworkError
import com.plcoding.cryptotracker.core.domin.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>,NetworkError>
}