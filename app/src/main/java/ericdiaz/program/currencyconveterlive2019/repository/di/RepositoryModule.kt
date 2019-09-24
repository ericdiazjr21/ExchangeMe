package ericdiaz.program.currencyconveterlive2019.repository.di

import dagger.Module
import dagger.Provides
import ericdiaz.program.currencyconveterlive2019.network.ExchangeRateService
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateNetworkRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    
    @Singleton
    @Provides
    fun providesExchangeRateNetworkRepository(exchangeRateService: ExchangeRateService): ExchangeRateNetworkRepository {
        return ExchangeRateNetworkRepository(exchangeRateService)
    }
}