package ericdiaz.program.currencyconveterlive2019.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ExchangeRateViewModel extends BaseViewModel {

    private final MutableLiveData<State> exchangeRateData = new MutableLiveData<>();
    private final BaseRepository exchangeRateRepository;

    @Inject
    public ExchangeRateViewModel(@NonNull final BaseRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void getConversionValue(@NonNull final String data,
                                   @NonNull final String baseCurrency,
                                   String foreignCurrency,
                                   String baseCurrencyAmount) {
        addDisposables(
          exchangeRateRepository
            .requestExchangeRates(data, baseCurrency)
            .delaySubscription(
              Completable.fromAction(() -> exchangeRateData.setValue(State.Loading.INSTANCE)))
            .map(exchangeRateResponse -> {

                Double baseAmount = Double.valueOf(baseCurrencyAmount);
                Double conversionRate = exchangeRateResponse.getRatesMap().get(foreignCurrency);

                return conversionRate != null ? String.valueOf((baseAmount * conversionRate)) : "Error, value is null";
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
              exchangeRateResponse -> exchangeRateData.setValue(new State.Success(exchangeRateResponse)),
              throwable -> exchangeRateData.setValue(new State.Failure(throwable))));
    }

    public LiveData<State> getExchangeRateData() {
        return exchangeRateData;
    }
}
