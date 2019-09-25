package ericdiaz.program.currencyconveterlive2019.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun addDisposables(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}