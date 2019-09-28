package ericdiaz.program.currencyconveterlive2019.repository;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import ericdiaz.program.data.model.ExchangeRateResponse;
import ericdiaz.program.data.network.ExchangeRateService;
import io.reactivex.Single;

public class ExchangeRateNetworkRepository implements BaseRepository {

    private final ExchangeRateService exchangeRateService;

    @Inject
    public ExchangeRateNetworkRepository(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public Single<ExchangeRateResponse> requestExchangeRates(@NonNull final String date,
                                                             @NonNull final String baseCurrency) {
        return exchangeRateService.getExchangeRates(date, baseCurrency);
    }
}
