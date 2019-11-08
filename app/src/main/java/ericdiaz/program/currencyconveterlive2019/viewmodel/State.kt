package ericdiaz.program.currencyconveterlive2019.viewmodel

import ericdiaz.program.data.model.CurrencyProfile

sealed class State {

    data class Success(val conversionValue: String = "not available",
                       val conversionRate: String = "not available",
                       val lastUpdated: String = "not available",
                       val currencyProfileMap: Map<String, CurrencyProfile> = emptyMap()) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()

    object Zero : State()
}