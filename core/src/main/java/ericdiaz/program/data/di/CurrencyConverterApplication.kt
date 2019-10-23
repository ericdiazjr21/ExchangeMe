package ericdiaz.program.data.di

import android.app.Application
import timber.log.Timber

class CurrencyConverterApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        appComponent.inject(this)

        Timber.plant(Timber.DebugTree())
    }

}