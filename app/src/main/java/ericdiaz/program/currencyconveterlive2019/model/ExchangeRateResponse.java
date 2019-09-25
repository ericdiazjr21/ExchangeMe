package ericdiaz.program.currencyconveterlive2019.model;

import com.google.gson.annotations.SerializedName;

public final class ExchangeRateResponse {

    private final Rates rates;
    @SerializedName("base")
    private final String baseCurrency;
    private final String date;
    public static final ExchangeRateResponse EMPTY = new ExchangeRateResponse(null,"ABC","Never");

    public ExchangeRateResponse(Rates rates, String baseCurrency, String date) {
        this.rates = rates;
        this.baseCurrency = baseCurrency;
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
          "rates=" + rates +
          ", baseCurrency='" + baseCurrency + '\'' +
          ", date='" + date + '\'' +
          '}';
    }
}
