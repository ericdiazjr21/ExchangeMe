package ericdiaz.program.currencyconveterlive2019.model;

public final class ExchangeRateResponse {

    private final Rates[] rates;
    private final String base;
    private final String date;

    public ExchangeRateResponse(Rates[] rates, String base, String date) {
        this.rates = rates;
        this.base = base;
        this.date = date;
    }

    public Rates[] getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }
}
