package org.weibeld.bestprice;

import org.weibeld.bestprice.ExchangeService.Currency;

public class ShopPrice {

  private String mShop;
  private double mPrice;
  private Currency mCurrency;

  public ShopPrice(String shop, double price, Currency currency) {
    mShop = shop;
    mPrice = price;
    mCurrency = currency;
  }

  public String getShop() {
    return mShop;
  }

  public double getPrice() {
    return mPrice;
  }

  public Currency getCurrency() {
    return mCurrency;
  }

  public void convertCurrency(double rate, Currency toCurrency) {
    mPrice = mPrice * rate;
    mCurrency = toCurrency;
  }

  @Override
  public String toString() {
    return String.format("%s: %.2f %s", mShop, mPrice, mCurrency.name());
  }

}
