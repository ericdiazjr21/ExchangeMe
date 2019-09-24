package ericdiaz.program.currencyconveterlive2019.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CurrencyConverterApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidDispatcher: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {

        return androidDispatcher
    }


}