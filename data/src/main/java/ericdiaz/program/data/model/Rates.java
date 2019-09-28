package ericdiaz.program.data.model;

import com.google.gson.annotations.SerializedName;

public final class Rates {

    @SerializedName("CAD")
    private final double canadianDollar;
    @SerializedName("HKD")
    private final double hongKongDollar;
    @SerializedName("ISK")
    private final double icelandicKróna;
    @SerializedName("PHP")
    private final double philippinePeso;
    @SerializedName("DKK")
    private final double danishKrone;
    @SerializedName("HUF")
    private final double hungarianForint;
    @SerializedName("CZK")
    private final double czechKoruna;
    @SerializedName("AUD")
    private final double australianDollar;
    @SerializedName("RON")
    private final double romanianLeu;
    @SerializedName("SEK")
    private final double swedishKrona;
    @SerializedName("IDR")
    private final double indonesianRupiah;
    @SerializedName("INR")
    private final double indianRupee;
    @SerializedName("BRL")
    private final double brazilianReal;
    @SerializedName("RUB")
    private final double russianRuble;
    @SerializedName("HRK")
    private final double croatianKuna;
    @SerializedName("JPY")
    private final double japaneseYen;
    @SerializedName("TYB")
    private final double thaiBaht;
    @SerializedName("CHF")
    private final double swissFranc;
    @SerializedName("SGD")
    private final double singaporeDollar;
    @SerializedName("PLN")
    private final double polandzłoty;
    @SerializedName("BGN")
    private final double bulgarianLev;
    @SerializedName("TRY")
    private final double turkishlira;
    @SerializedName("CNY")
    private final double chineseYuan;
    @SerializedName("NOK")
    private final double norwegianKrone;
    @SerializedName("NZD")
    private final double newZealandDollar;
    @SerializedName("ZAR")
    private final double southAfricanRand;
    @SerializedName("USD")
    private final double unitedStatesDollar;
    @SerializedName("MXN")
    private final double mexicanPeso;
    @SerializedName("INS")
    private final double israeliNewShekel;
    @SerializedName("GBP")
    private final double poundsterling;
    @SerializedName("KRW")
    private final double southKoreanwon;
    @SerializedName("MYR")
    private final double malaysianRinggit;
    @SerializedName("EUR")
    private final double euro;


    public Rates(double cad, double hkd, double isk, double php, double dkk, double huf, double czk, double aud, double ron, double sek, double idr, double inr, double brl, double rub, double hrk, double jpy, double thb, double chf, double sgd, double pln, double bgn, double aTry, double cny, double nok, double nzd, double zar, double usd, double mxn, double ils, double gbp, double krw, double myr, double euro) {
        this.canadianDollar = cad;
        this.hongKongDollar = hkd;
        this.icelandicKróna = isk;
        this.philippinePeso = php;
        this.danishKrone = dkk;
        this.hungarianForint = huf;
        this.czechKoruna = czk;
        this.australianDollar = aud;
        this.romanianLeu = ron;
        this.swedishKrona = sek;
        this.indonesianRupiah = idr;
        this.indianRupee = inr;
        this.brazilianReal = brl;
        this.russianRuble = rub;
        this.croatianKuna = hrk;
        this.japaneseYen = jpy;
        this.thaiBaht = thb;
        this.swissFranc = chf;
        this.singaporeDollar = sgd;
        this.polandzłoty = pln;
        this.bulgarianLev = bgn;
        this.turkishlira = aTry;
        this.chineseYuan = cny;
        this.norwegianKrone = nok;
        this.newZealandDollar = nzd;
        this.southAfricanRand = zar;
        this.unitedStatesDollar = usd;
        this.mexicanPeso = mxn;
        this.israeliNewShekel = ils;
        this.poundsterling = gbp;
        this.southKoreanwon = krw;
        this.malaysianRinggit = myr;
        this.euro = euro;
    }

    public double getCanadianDollar() {
        return canadianDollar;
    }

    public double getHongKongDollar() {
        return hongKongDollar;
    }

    public double getIcelandicKróna() {
        return icelandicKróna;
    }

    public double getPhilippinePeso() {
        return philippinePeso;
    }

    public double getDanishKrone() {
        return danishKrone;
    }

    public double getHungarianForint() {
        return hungarianForint;
    }

    public double getCzechKoruna() {
        return czechKoruna;
    }

    public double getAustralianDollar() {
        return australianDollar;
    }

    public double getRomanianLeu() {
        return romanianLeu;
    }

    public double getSwedishKrona() {
        return swedishKrona;
    }

    public double getIndonesianRupiah() {
        return indonesianRupiah;
    }

    public double getIndianRupee() {
        return indianRupee;
    }

    public double getBrazilianReal() {
        return brazilianReal;
    }

    public double getRussianRuble() {
        return russianRuble;
    }

    public double getCroatianKuna() {
        return croatianKuna;
    }

    public double getJapaneseYen() {
        return japaneseYen;
    }

    public double getThaiBaht() {
        return thaiBaht;
    }

    public double getSwissFranc() {
        return swissFranc;
    }

    public double getSingaporeDollar() {
        return singaporeDollar;
    }

    public double getPolandzłoty() {
        return polandzłoty;
    }

    public double getBulgarianLev() {
        return bulgarianLev;
    }

    public double getTurkishlira() {
        return turkishlira;
    }

    public double getChineseYuan() {
        return chineseYuan;
    }

    public double getNorwegianKrone() {
        return norwegianKrone;
    }

    public double getNewZealandDollar() {
        return newZealandDollar;
    }

    public double getSouthAfricanRand() {
        return southAfricanRand;
    }

    public double getUnitedStatesDollar() {
        return unitedStatesDollar;
    }

    public double getMexicanPeso() {
        return mexicanPeso;
    }

    public double getIsraeliNewShekel() {
        return israeliNewShekel;
    }

    public double getPoundsterling() {
        return poundsterling;
    }

    public double getSouthKoreanwon() {
        return southKoreanwon;
    }

    public double getMalaysianRinggit() {
        return malaysianRinggit;
    }

    public double getEuro() {
        return euro;
    }

    @Override
    public String toString() {
        return "Rates{" +
          "canadianDollar=" + canadianDollar +
          ", hongKongDollar=" + hongKongDollar +
          ", icelandicKróna=" + icelandicKróna +
          ", philippinePeso=" + philippinePeso +
          ", danishKrone=" + danishKrone +
          ", hungarianForint=" + hungarianForint +
          ", czechKoruna=" + czechKoruna +
          ", australianDollar=" + australianDollar +
          ", romanianLeu=" + romanianLeu +
          ", swedishKrona=" + swedishKrona +
          ", indonesianRupiah=" + indonesianRupiah +
          ", indianRupee=" + indianRupee +
          ", brazilianReal=" + brazilianReal +
          ", russianRuble=" + russianRuble +
          ", croatianKuna=" + croatianKuna +
          ", japaneseYen=" + japaneseYen +
          ", thaiBaht=" + thaiBaht +
          ", swissFranc=" + swissFranc +
          ", singaporeDollar=" + singaporeDollar +
          ", polandzłoty=" + polandzłoty +
          ", bulgarianLev=" + bulgarianLev +
          ", turkishlira=" + turkishlira +
          ", chineseYuan=" + chineseYuan +
          ", norwegianKrone=" + norwegianKrone +
          ", newZealandDollar=" + newZealandDollar +
          ", southAfricanRand=" + southAfricanRand +
          ", unitedStatesDollar=" + unitedStatesDollar +
          ", mexicanPeso=" + mexicanPeso +
          ", israeliNewShekel=" + israeliNewShekel +
          ", poundsterling=" + poundsterling +
          ", southKoreanwon=" + southKoreanwon +
          ", malaysianRinggit=" + malaysianRinggit +
          ", euro=" + euro +
          '}';
    }
}