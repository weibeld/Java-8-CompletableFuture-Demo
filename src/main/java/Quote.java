package org.weibeld.bestprice;

/* Message from Shop to DiscountService */
public class Quote {

  private final String shopName;
  private final double price;
  private final DiscountService.Code discountCode;

  public Quote(String shopName, double price, DiscountService.Code code) {
    this.shopName = shopName;
    this.price = price;
    this.discountCode = code;
  }

  public static Quote parse(String str) {
    String[] split = str.split(":");
    return new Quote(split[0], Double.parseDouble(split[1]),
      DiscountService.Code.valueOf(split[2]));
  }

  public String getShopName() {
    return shopName;
  }

  public double getPrice() {
    return price;
  }

  public DiscountService.Code getDiscountCode() {
    return discountCode;
  }

}
