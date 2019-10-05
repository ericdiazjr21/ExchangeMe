package ericdiaz.program.currencyconveterlive2019.view.di

import android.content.Context
import android.widget.ArrayAdapter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ericdiaz.program.currencyconveterlive2019.R
import ericdiaz.program.currencyconveterlive2019.di.CurrencyConverterApplication
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

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesCurrencyListArray(application: CurrencyConverterApplication): ArrayAdapter<CharSequence> {
            return ArrayAdapter.createFromResource(
                    application.applicationContext,
                    R.array.currency_list,
                    R.layout.support_simple_spinner_dropdown_item
            )
        }
    }
}