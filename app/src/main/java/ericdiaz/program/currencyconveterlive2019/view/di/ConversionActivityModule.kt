package ericdiaz.program.currencyconveterlive2019.view.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ericdiaz.program.currencyconveterlive2019.view.ConversionActivity
import ericdiaz.program.currencyconveterlive2019.viewmodel.di.ViewModelModule


@Module
abstract class ConversionActivityModule {
    /*
        Will create a subComponent for any Android Framework type to
        avoid creating subComponents manually
     */
    @ConversionActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun conversionActivity(): ConversionActivity
}