package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.core.data.networking.constructUrl
import com.plcoding.cryptotracker.core.data.networking.safeCall
import com.plcoding.cryptotracker.core.domin.util.NetworkError
import com.plcoding.cryptotracker.core.domin.util.Result
import com.plcoding.cryptotracker.core.domin.util.map
import com.plcoding.cryptotracker.crypto.data.Mapper.toCoin
import com.plcoding.cryptotracker.crypto.data.Mapper.toCoinPrice
import com.plcoding.cryptotracker.crypto.data.dto.CoinHistoryDto
import com.plcoding.cryptotracker.crypto.data.dto.CoinsResponseDto
import com.plcoding.cryptotracker.crypto.domin.Coin
import com.plcoding.cryptotracker.crypto.domin.CoinDataSource
import com.plcoding.cryptotracker.crypto.domin.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

// Repository Implementation
class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            //map each item in list of coinDto to coin
            response.data.map { it.toCoin() }

        }
    }

    override suspend fun getCoinsHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()


        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice()}

        }
    }

}