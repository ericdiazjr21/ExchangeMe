package ericdiaz.program.data.utils

import android.content.Context
import ericdiaz.program.data.di.CurrencyConverterApplication
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(application: CurrencyConverterApplication) {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "ericdiaz.program.currencyconverterlive1019.sharedprefs"
        const val FIRST_TIME_INSTALL = "key for tracking first time download"
    }

    private val sharedPreferences = application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun editSharedPreferences(key: String, value: Any): Boolean {
        return sharedPreferences.edit()
                .putBoolean(key, value as Boolean)
                .commit()
    }

}