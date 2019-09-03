package ericdiaz.program.currencyconveterlive2019;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class ViewModelTest {

    private ExchangeRateViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = Mockito.mock(ExchangeRateViewModel.class);
    }

    @Test
    public void test1() {
        String date = "8/28/19";
        String baseCurrency = "USD";
        when(viewModel.getRates(date, baseCurrency))
          .thenReturn(Single.just(new ExchangeRateResponse(null, baseCurrency, date)));

        TestObserver<ExchangeRateResponse> responseTestObserver = viewModel.getRates(date, baseCurrency).test();

        responseTestObserver
          .assertNoErrors()
          .assertValue(response -> response.getBaseCurrency().equals(baseCurrency))
          .assertValue(response -> response.getDate().equals(date))
          .assertValue(response -> response.getRates() == null).dispose();
    }
}
