package ericdiaz.program.currencyconveterlive2019.viewmodel.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ericdiaz.program.currencyconveterlive2019.view.ConversionActivity
import ericdiaz.program.currencyconveterlive2019.view.di.ConversionActivityScope
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModelFactory

@Module
class ViewModelModule {

    @Provides
    @ConversionActivityScope
    fun providesExchangeViewModel(conversionActivity: ConversionActivity,
                                  exchangeRateViewModelFactory: ExchangeRateViewModelFactory): ExchangeRateViewModel {
        return ViewModelProviders.of(conversionActivity, exchangeRateViewModelFactory).get(ExchangeRateViewModel::class.java)
    }
}