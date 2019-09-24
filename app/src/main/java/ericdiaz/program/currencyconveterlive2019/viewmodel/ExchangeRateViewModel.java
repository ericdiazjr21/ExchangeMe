package ericdiaz.program.currencyconveterlive2019.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;

public class ExchangeRateViewModel extends BaseViewModel {

    private static final String TAG = "ExchangeRateViewModel";
    private BaseRepository exchangeRateRepository;

    @Inject
    public ExchangeRateViewModel(@NonNull final BaseRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void getRates(@NonNull final String data,
                         @NonNull final String baseCurrency) {
        addDisposables(
          exchangeRateRepository
            .requestExchangeRates(data, baseCurrency)
            .subscribe(
              exchangeRateResponse -> Log.d(TAG, "getRates: " + exchangeRateResponse),
              throwable -> Log.d(TAG, "getRates: " + throwable.getLocalizedMessage())));
    }


}
