package ericdiaz.program.currencyconveterlive2019.view.di

import android.content.Context
import android.content.res.Resources
import android.widget.ArrayAdapter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ericdiaz.program.currencyconveterlive2019.R
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
        fun providesCurrencyListArray(context: Context): ArrayAdapter<CharSequence> {
            return ArrayAdapter.createFromResource(
                    context,
                    R.array.currency_list,
                    R.layout.support_simple_spinner_dropdown_item
            )
        }

        @Provides
        @JvmStatic
        fun providesCurrencyListStringArray(resources: Resources): Array<String> {
            return resources.getStringArray(R.array.currency_list);
        }
    }
}