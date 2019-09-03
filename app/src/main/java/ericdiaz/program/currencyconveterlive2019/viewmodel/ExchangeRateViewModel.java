package ericdiaz.program.currencyconveterlive2019.viewmodel;

import android.support.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import io.reactivex.Single;

public class ExchangeRateViewModel {

    private BaseRepository exchangeRateRepository;

    public ExchangeRateViewModel(@NonNull final BaseRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public Single<ExchangeRateResponse> getRates(@NonNull final String data,
                                                 @NonNull final String baseCurrency) {
        return exchangeRateRepository.requestExchangeRates(data, baseCurrency);
    }
}
