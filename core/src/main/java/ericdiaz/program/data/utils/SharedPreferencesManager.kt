package ericdiaz.program.data.utils

import android.content.Context
import ericdiaz.program.data.di.CurrencyConverterApplication
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(application: CurrencyConverterApplication) {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "ericdiaz.program.currencyconverterlive1019.sharedprefs"
        private const val FIRST_TIME_INSTALL = "key for tracking first time download"
    }

    private val sharedPreferences = application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setFirstTimeInstallState(value: Boolean): Boolean {
        return sharedPreferences.edit()
                .putBoolean(FIRST_TIME_INSTALL, value)
                .commit()
    }

    fun isFirstTimeInstall(): Boolean {
        return sharedPreferences.getBoolean(FIRST_TIME_INSTALL, true)
    }

}