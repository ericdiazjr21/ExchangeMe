package ericdiaz.program.currencyconveterlive2019.di

import dagger.Component
import ericdiaz.program.currencyconveterlive2019.view.ConversionActivity
import ericdiaz.program.currencyconveterlive2019.view.di.ConversionActivityModule
import ericdiaz.program.currencyconveterlive2019.view.di.ConversionActivityScope
import ericdiaz.program.currencyconveterlive2019.viewmodel.di.ViewModelModule
import ericdiaz.program.data.di.AppComponent
import ericdiaz.program.data.di.CurrencyConverterApplication


@Component(
        modules = [
            ConversionActivityModule::class,
            ViewModelModule::class],
        dependencies = [AppComponent::class])
@ConversionActivityScope
interface ConversionActivityComponent {
    fun inject(conversionActivity: ConversionActivity)
}

fun ConversionActivity.inject(){
    DaggerConversionActivityComponent
            .builder()
            .viewModelModule(ViewModelModule(this))
            .appComponent((application as CurrencyConverterApplication).appComponent)
            .build()
            .inject(this)
}