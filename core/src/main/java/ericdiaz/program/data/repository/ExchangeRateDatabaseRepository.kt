package ericdiaz.program.data.repository

import com.squareup.sqldelight.runtime.rx.asObservable
import com.squareup.sqldelight.runtime.rx.mapToOne
import ericdiaz.program.data.ExchangeRatesQueries
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.CurrencyProfile
import ericdiaz.program.data.model.ExchangeRateResponse
import io.reactivex.Single
import javax.inject.Inject

//TODO: address threading in these function
open class ExchangeRateDatabaseRepository @Inject constructor(exchangeRateDatabase: ExchangeRateDatabase) : BaseRepository {

    private val exchangeRatesDatabaseQueries: ExchangeRatesQueries = exchangeRateDatabase.exchangeRatesQueries

    override fun requestExchangeRates(date: String, baseCurrency: String): Single<ExchangeRateResponse> {
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
                )
                .asObservable()
                .mapToOne()
                .singleOrError()
    }

    override fun requestCurrencyProfiles(): Single<Map<String, CurrencyProfile>> {
        return exchangeRatesDatabaseQueries
                .selectCurrencyProfileMap()
                .asObservable()
                .mapToOne()
                .singleOrError()
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