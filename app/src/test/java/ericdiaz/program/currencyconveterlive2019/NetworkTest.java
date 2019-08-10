package ericdiaz.program.currencyconveterlive2019;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.network.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class NetworkTest {

    @Mock
    private ExchangeRateService exchangeRateService;
    private CompositeDisposable compositeDisposable;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Test
    public void testExchangeRateServiceResponse() {
        //given
        ExchangeRateResponse response = new ExchangeRateResponse(null, "USD", "10-10-1992");

        //when
        when(exchangeRateService.getExchangeRates(null, null))
          .thenReturn(Single.just(response));

        //then
        compositeDisposable.add(exchangeRateService.getExchangeRates(null, null)
          .subscribe(exchangeRateResponse -> assertEquals("USD", exchangeRateResponse.getBaseCurrency())));

    }

    @After
    public void tearDown() {
        compositeDisposable.dispose();
    }
}