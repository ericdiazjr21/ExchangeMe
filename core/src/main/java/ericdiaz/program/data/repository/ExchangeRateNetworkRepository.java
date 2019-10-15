package ericdiaz.program.data.repository;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Override
    public Single<ExchangeRateResponse> requestExchangeRates(@NonNull final String date,
                                                             @NonNull final String baseCurrency) {
        return exchangeRateService.getExchangeRates(date, baseCurrency);
    }
}
