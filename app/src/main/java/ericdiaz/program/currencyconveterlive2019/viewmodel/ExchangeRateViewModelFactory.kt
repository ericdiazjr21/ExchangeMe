package ericdiaz.program.currencyconveterlive2019.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateNetworkRepository
import javax.inject.Inject

class ExchangeRateViewModelFactory @Inject constructor(private val exchangeRateNetworkRepository: ExchangeRateNetworkRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeRateViewModel::class.java)) {
            return ExchangeRateViewModel(exchangeRateNetworkRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Type not registered")
        }
    }
}