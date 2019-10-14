package ericdiaz.program.currencyconveterlive2019.view.di

import android.content.Context
import android.content.res.Resources
import android.widget.ArrayAdapter
import dagger.Module
import dagger.Provides
import ericdiaz.program.currencyconveterlive2019.R


@Module
abstract class ConversionActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesCurrencyListArray(context: Context): ArrayAdapter<CharSequence> {
            return ArrayAdapter.createFromResource(
                    context,
                    R.array.currency_list,
                    R.layout.support_simple_spinner_dropdown_item
            )
        }

        @Provides
        @JvmStatic
        @ConversionActivityScope
        fun providesCurrencyListStringArray(resources: Resources): Array<String> {
            return resources.getStringArray(R.array.currency_list);
        }
    }
}