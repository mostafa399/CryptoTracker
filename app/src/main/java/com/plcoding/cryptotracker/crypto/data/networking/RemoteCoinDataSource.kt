package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.core.data.networking.constructUrl
import com.plcoding.cryptotracker.core.data.networking.safeCall
import com.plcoding.cryptotracker.core.domin.util.NetworkError
import com.plcoding.cryptotracker.core.domin.util.Result
import com.plcoding.cryptotracker.core.domin.util.map
import com.plcoding.cryptotracker.crypto.data.Mapper.toCoin
import com.plcoding.cryptotracker.crypto.data.dto.CoinsResponseDto
import com.plcoding.cryptotracker.crypto.domin.Coin
import com.plcoding.cryptotracker.crypto.domin.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
// Repository Implementation
class RemoteCoinDataSource(
    private val httpClient: HttpClient
) :CoinDataSource{
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map {response->
            //map each item in list of coinDto to coin
            response.data.map {it.toCoin()}

        }
    }

}