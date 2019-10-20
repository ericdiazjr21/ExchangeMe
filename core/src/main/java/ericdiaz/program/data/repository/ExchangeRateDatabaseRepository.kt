package ericdiaz.program.data.repository

import ericdiaz.program.data.ExchangeRatesQueries
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.CurrencyProfile
import ericdiaz.program.data.model.ExchangeRateResponse
import io.reactivex.Single
import javax.inject.Inject

//TODO: address threading in these function
class ExchangeRateDatabaseRepository @Inject constructor(exchangeRateDatabase: ExchangeRateDatabase) : BaseRepository {

    private val exchangeRatesDatabaseQueries: ExchangeRatesQueries = exchangeRateDatabase.exchangeRatesQueries

    override fun requestExchangeRates(date: String, baseCurrency: String): Single<ExchangeRateResponse> {
        return Single.just(
                exchangeRatesDatabaseQueries
                        .selectByDateAndBaseCurrency(
                                date,
                                baseCurrency,
                                mapper = { cachedDate, cachedRateMap, cachedBaseCurrency ->
                                    ExchangeRateResponse(
                                            date = cachedDate,
                                            ratesMap = cachedRateMap,
                                            baseCurrency = cachedBaseCurrency)
                                }
                        ).executeAsOneOrNull()
        )
    }

    override fun requestCurrencyProfiles(): Single<Map<String, CurrencyProfile>> {
        return Single.just(exchangeRatesDatabaseQueries.selectCurrencyProfileMap().executeAsOneOrNull())
    }

    fun insertExchangeRateResponse(exchangeRateResponse: ExchangeRateResponse) {
        exchangeRatesDatabaseQueries
                .insertExchangeRates(
                        exchangeRateResponse.date,
                        exchangeRateResponse.ratesMap,
                        exchangeRateResponse.baseCurrency
                )
    }

    fun insertCurrencyProfileMap(currencyProfileMap: Map<String, CurrencyProfile>) {
        exchangeRatesDatabaseQueries.insertCurrencyProfileMap(currencyProfileMap)
    }

}