package org.weibeld.bestprice;

import org.weibeld.bestprice.ExchangeService.Currency;
import org.weibeld.bestprice.DiscountService.DiscountCode;

/* Message from Shop to DiscountService */
public class Quote {

  private final String shopName;
  private final double price;
  private final DiscountCode discountCode;
  private final Currency currency;

  public Quote(String shopName, double price, DiscountCode code,
    Currency currency) {
    this.shopName = shopName;
    this.price = price;
    this.discountCode = code;
    this.currency = currency;
  }

  public static Quote parse(String str) {
    String[] split = str.split(":");
    return new Quote(split[0], Double.parseDouble(split[1]),
      DiscountCode.valueOf(split[2]), Currency.valueOf(split[3]));
  }

  public String getShopName() {
    return shopName;
  }

  public double getPrice() {
    return price;
  }

  public DiscountCode getDiscountCode() {
    return discountCode;
  }

  public Currency getCurrency() {
    return currency;
  }

}
