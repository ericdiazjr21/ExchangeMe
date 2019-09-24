package ericdiaz.program.currencyconveterlive2019.viewmodel.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ericdiaz.program.currencyconveterlive2019.view.MainActivity
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModelFactory
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesExchangeViewModel(mainActivity: MainActivity,
                                  exchangeRateViewModelFactory: ExchangeRateViewModelFactory): ExchangeRateViewModel {
        return ViewModelProviders.of(mainActivity, exchangeRateViewModelFactory).get(ExchangeRateViewModel::class.java)
    }
}