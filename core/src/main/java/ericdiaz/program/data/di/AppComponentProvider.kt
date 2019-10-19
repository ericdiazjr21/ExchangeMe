package ericdiaz.program.data.di

import android.content.Context
import android.content.res.Resources
import ericdiaz.program.data.repository.ExchangeRateDatabaseRepository
import ericdiaz.program.data.repository.ExchangeRateNetworkRepository

interface AppComponentProvider {

    fun providesContext(): Context

    fun providesResources(): Resources

    fun providesNetworkRepository(): ExchangeRateNetworkRepository

    fun providesDatabaseRepository(): ExchangeRateDatabaseRepository

}