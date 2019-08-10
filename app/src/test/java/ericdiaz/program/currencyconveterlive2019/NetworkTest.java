package ericdiaz.program.currencyconveterlive2019;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.currencyconveterlive2019.network.ExchangeRateService;
import ericdiaz.program.currencyconveterlive2019.network.RetrofitServiceGenerator;


public class NetworkTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testRetrofitServiceGenerator() {
        Assert.assertTrue(RetrofitServiceGenerator.getExchangeRateService() instanceof ExchangeRateService);
    }


}