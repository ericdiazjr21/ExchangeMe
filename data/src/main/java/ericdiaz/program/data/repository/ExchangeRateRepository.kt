package ericdiaz.program.data.repository

import ericdiaz.program.data.ExchangeRatesQueries
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.ExchangeRateResponse
import javax.inject.Inject

class ExchangeRateRepository @Inject constructor(exchangeRateDatabase: ExchangeRateDatabase) : BaseRepository {

    private val exchangeRatesDatabaseQueries: ExchangeRatesQueries = exchangeRateDatabase.exchangeRatesQueries

    fun insertExchangeRateResponse(exchangeRateResponse: ExchangeRateResponse) {
        exchangeRatesDatabaseQueries
                .insertExchangeRates(
                        exchangeRateResponse.date,
                        exchangeRateResponse.ratesMap,
                        exchangeRateResponse.baseCurrency
                )
    }

    fun getCachedRatesByDateAndBaseCurrency(date: String,
                                            baseCurrency: String): ExchangeRateResponse {
        return exchangeRatesDatabaseQueries
                .selectByDateAndBaseCurrency(
                        date,
                        baseCurrency,
                        mapper = { cachedDate, cachedRateMap, cachedBaseCurrency ->
                            ExchangeRateResponse(
                                    date = cachedDate,
                                    ratesMap = cachedRateMap,
                                    baseCurrency = cachedBaseCurrency)
                        }
                ).executeAsOne()

    }

}