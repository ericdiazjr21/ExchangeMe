package ericdiaz.program.currencyconveterlive2019.viewmodel

import ericdiaz.program.data.model.ExchangeRateResponse

sealed class State {

    data class Success(val exchangeRateResponse: ericdiaz.program.data.model.ExchangeRateResponse) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()
}