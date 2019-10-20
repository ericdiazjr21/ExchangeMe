package ericdiaz.program.currencyconveterlive2019.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ericdiaz.program.currencyconveterlive2019.extensions.getExchangeValue
import ericdiaz.program.data.repository.BaseRepository
import ericdiaz.program.data.repository.ExchangeRateDatabaseRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ExchangeRateViewModel(private val exchangeRateNetworkRepository: BaseRepository,
                            private val exchangeRateDatabaseRepository: ExchangeRateDatabaseRepository) : BaseViewModel() {

    private val exchangeRateData = MutableLiveData<State>()
    lateinit var baseCurrency: String
    lateinit var foreignCurrency: String
    lateinit var baseCurrencyAmount: String

    fun getConversionValue(date: String = "latest") {
        addDisposables(
                exchangeRateNetworkRepository
                        .requestExchangeRates(date, baseCurrency)
                        .delaySubscription(
                                Completable.fromAction { exchangeRateData.setValue(State.Loading) })
                        .map { (_, ratesMap) ->

                            val conversionRate = ratesMap[foreignCurrency]

                            conversionRate?.getExchangeValue(baseCurrencyAmount)
                                    ?: "Error, value is null"
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { exchangeRateResponse -> exchangeRateData.setValue(State.Success(exchangeRateResponse)) },
                                { throwable -> exchangeRateData.setValue(State.Failure(throwable)) }))
    }

    fun getExchangeRateData(): LiveData<State> {
        return exchangeRateData
    }
}
