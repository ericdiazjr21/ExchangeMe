package ericdiaz.program.currencyconveterlive2019.viewmodel;

import android.support.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateRepository;

public class ExchangeRateViewModel {

    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateViewModel(@NonNull final ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void getRates(@NonNull final String data,
                         @NonNull final String baseCurrency) {
        exchangeRateRepository.requestExchangeRates(data, baseCurrency);
    }
}
