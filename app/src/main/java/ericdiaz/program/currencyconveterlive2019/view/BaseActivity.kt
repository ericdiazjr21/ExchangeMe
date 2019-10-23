package ericdiaz.program.currencyconveterlive2019.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ericdiaz.program.data.di.CurrencyConverterApplication
import ericdiaz.program.data.utils.SharedPreferencesManager

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private val sharedPreferencesManager by lazy {
        SharedPreferencesManager(application as CurrencyConverterApplication)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (sharedPreferencesManager.isFirstTimeInstall()) {
            sharedPreferencesManager.setFirstTimeInstallState(false)
        }
    }

    fun isFirstTimeInstall(): Boolean {
        return sharedPreferencesManager.isFirstTimeInstall()
    }
}