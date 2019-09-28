package ericdiaz.program.currencyconveterlive2019.view.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ericdiaz.program.currencyconveterlive2019.view.ConversionActivity


@Module
abstract class MainActivityModule {
    /*
        Will create a sub component for any Android Framework type to
        avoid creating sub components manually
     */
    @ContributesAndroidInjector
    abstract fun mainActivity(): ConversionActivity

    @ContributesAndroidInjector
    abstract fun any(): Any
}