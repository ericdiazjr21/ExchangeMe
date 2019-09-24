package ericdiaz.program.currencyconveterlive2019;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateRepository;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    private BaseRepository exchangeRateRepository;
    private ExchangeRateViewModel exchangeRateViewModel;

    @Before
    public void setUp() {
        exchangeRateRepository = mock(ExchangeRateRepository.class);
        exchangeRateViewModel = new ExchangeRateViewModel(exchangeRateRepository);
    }

    @Test
    public void testRequestExchangeRatesMethodCallWhenViewModelGetRatesMethodCalled() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";
        ExchangeRateResponse response = new ExchangeRateResponse(null, null, null);
        when(exchangeRateViewModel.getRates(date, baseCurrency)).thenReturn(Single.just(response));

        //when
        exchangeRateViewModel.getRates(date, baseCurrency);

        //then
        verify(exchangeRateRepository).requestExchangeRates(date, baseCurrency);

    }

    @After
    public void tearDown() {
    }
}
