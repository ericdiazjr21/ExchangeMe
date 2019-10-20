package ericdiaz.program.data.repository

import ericdiaz.program.data.model.CurrencyProfile
import ericdiaz.program.data.model.ExchangeRateResponse
import io.reactivex.Single

interface BaseRepository {
    fun requestExchangeRates(date: String, baseCurrency: String): Single<ExchangeRateResponse>

    fun requestCurrencyProfiles(): Single<Map<String, CurrencyProfile>>
}