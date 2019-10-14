package ericdiaz.program.data.repository

import ericdiaz.program.data.ExchangeRatesQueries
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.ExchangeRateResponse
import io.reactivex.Single
import javax.inject.Inject

//TODO: address threading in these function
class ExchangeRateDatabaseRepository @Inject constructor(exchangeRateDatabase: ExchangeRateDatabase) : BaseRepository {

    override fun requestExchangeRates(date: String, baseCurrency: String): Single<ExchangeRateResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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