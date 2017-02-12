package models;

/**
 * Created by 33558 on 12.02.2017.
 */
public class Exchanger {
    private double pairUsdUah = 27.30;
    private double pairUsdEur = 0.93;
    private double pairEurUsd = 1.05;
    private double pairEurUah = 29;
    private double pairUahUsd = 0.0361;
    private double pairUahEur = 0.0339;


    public double getExchangeUsdUah(double usd) {
        return usd * pairUsdUah;
    }

    public void setPairUsdUah(double pairUsdUah) {
        this.pairUsdUah = pairUsdUah;
    }

    public double getExchangeUsdEur(double usd) {
        return usd * pairUsdEur;
    }

    public void setPairUsdEur(double pairUsdEur) {
        this.pairUsdEur = pairUsdEur;
    }

    public double getExchangeEurUsd(double eur) {
        return eur * pairEurUsd;
    }

    public void setPairEurUsd(double pairEurUsd) {
        this.pairEurUsd = pairEurUsd;
    }

    public double getExchangeEurUah(double eur) {
        return eur * pairEurUah;
    }

    public void setPairEurUah(double pairEurUah) {
        this.pairEurUah = pairEurUah;
    }

    public double getExchangeUahUsd(double uah) {
        return uah * pairUahUsd;
    }

    public void setPairUahUsd(double pairUahUsd) {
        this.pairUahUsd = pairUahUsd;
    }

    public double getExchangeUahEur(double uah) {
        return uah * pairUahEur;
    }

    public void setPairUahEur(double pairUahEur) {
        this.pairUahEur = pairUahEur;
    }

    public Exchanger(double pairUsdUah, double pairUsdEur, double pairEurUsd, double pairEurUah, double pairUahUsd, double pairUahEur) {
        this.pairUsdUah = pairUsdUah;
        this.pairUsdEur = pairUsdEur;
        this.pairEurUsd = pairEurUsd;
        this.pairEurUah = pairEurUah;
        this.pairUahUsd = pairUahUsd;
        this.pairUahEur = pairUahEur;
    }

    public Exchanger() {
    }
}
