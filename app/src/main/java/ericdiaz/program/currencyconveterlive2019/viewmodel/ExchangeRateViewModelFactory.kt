package ericdiaz.program.currencyconveterlive2019.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ericdiaz.program.data.repository.ExchangeRateDatabaseRepository
import ericdiaz.program.data.repository.ExchangeRateNetworkRepository
import javax.inject.Inject

class ExchangeRateViewModelFactory @Inject constructor(
        private val exchangeRateNetworkRepository: ExchangeRateNetworkRepository,
        private val exchangeRateDatabaseRepository: ExchangeRateDatabaseRepository)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeRateViewModel::class.java)) {
            return ExchangeRateViewModel(
                    exchangeRateNetworkRepository,
                    exchangeRateDatabaseRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Type not registered")
        }
    }
}