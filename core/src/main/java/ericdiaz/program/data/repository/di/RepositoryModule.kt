package ericdiaz.program.data.repository.di

import dagger.Module
import dagger.Provides
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.network.CurrencyProfileService
import ericdiaz.program.data.network.ExchangeRateService
import ericdiaz.program.data.repository.ExchangeRateDatabaseRepository
import ericdiaz.program.data.repository.ExchangeRateNetworkRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesExchangeRateNetworkRepository(exchangeRateService: ExchangeRateService,
                                              currencyProfileService: CurrencyProfileService)
            : ExchangeRateNetworkRepository {
        return ExchangeRateNetworkRepository(exchangeRateService, currencyProfileService)
    }

    @Singleton
    @Provides
    fun providesExchangeRateDatabaseRepository(exchangeRateDatabase: ExchangeRateDatabase)
            : ExchangeRateDatabaseRepository {
        return ExchangeRateDatabaseRepository(exchangeRateDatabase)
    }
}