package ericdiaz.program.currencyconveterlive2019.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;

public class ExchangeRateViewModel extends BaseViewModel {

    private final MutableLiveData<State> exchangeRateData = new MutableLiveData<>();
    private final BaseRepository exchangeRateRepository;

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
              exchangeRateResponse -> exchangeRateData.setValue(new State.Success(exchangeRateResponse)),
              throwable -> exchangeRateData.setValue(new State.Failure(throwable))));
    }

    public LiveData<State> getExchangeRateData() {
        return exchangeRateData;
    }
}
