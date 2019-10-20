package ericdiaz.program.data.repository;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import ericdiaz.program.data.model.CurrencyProfile;
import ericdiaz.program.data.model.ExchangeRateResponse;
import ericdiaz.program.data.network.CurrencyProfileService;
import ericdiaz.program.data.network.ExchangeRateService;
import io.reactivex.Single;

public class ExchangeRateNetworkRepository implements BaseRepository {

    private final ExchangeRateService exchangeRateService;
    private final CurrencyProfileService currencyProfileService;

    @Inject
    public ExchangeRateNetworkRepository(ExchangeRateService exchangeRateService,
                                         CurrencyProfileService currencyProfileService) {
        this.exchangeRateService = exchangeRateService;
        this.currencyProfileService = currencyProfileService;
    }

    @NotNull
    @Override
    public Single<ExchangeRateResponse> requestExchangeRates(@NonNull final String date,
                                                             @NonNull final String baseCurrency) {
        return exchangeRateService.getExchangeRates(date, baseCurrency);
    }

    @NotNull
    @Override
    public Single<Map<String, CurrencyProfile>> requestCurrencyProfiles() {
        return currencyProfileService.getCurrencyProfileMap();
    }
}
