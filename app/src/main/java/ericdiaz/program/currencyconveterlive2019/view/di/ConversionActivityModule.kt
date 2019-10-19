package ericdiaz.program.currencyconveterlive2019.view.di

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import ericdiaz.program.currencyconveterlive2019.R
import ericdiaz.program.currencyconveterlive2019.view.adapter.CurrencyAdapter


@Module
abstract class ConversionActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesCurrencyAdapter(layoutInflater: LayoutInflater?,
                                    currencyList: Array<String>,
                                    imageResTestHolder: Int): CurrencyAdapter {
            return CurrencyAdapter(layoutInflater, currencyList, imageResTestHolder)
        }

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesLayoutInflater(context: Context): LayoutInflater? {
            return LayoutInflater.from(context)
        }

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesCurrencyListStringArray(resources: Resources): Array<String> {
            return resources.getStringArray(R.array.currency_list);
        }

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesCurrencyTestRes(): Int {
            return R.drawable.ic_check
        }
    }
}