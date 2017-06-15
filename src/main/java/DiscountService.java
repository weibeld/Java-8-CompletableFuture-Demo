package org.weibeld.bestprice;

/* Server
 * API:
 *   static String applyDiscount(Quote quote) */
public class DiscountService {

  private static final long DELAY = 2000L;

  public enum Code {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
    private final int percentage;
    Code(int percentage) {
      this.percentage = percentage;
    }
  }

  public static String applyDiscount(Quote quote) {
    double discountedPrice = apply(quote.getPrice(), quote.getDiscountCode());
    Util.delay(DELAY);
    return String.format("%s: %.2f", quote.getShopName(), discountedPrice);
  }

  private static double apply(double price, Code code) {
    return price * (100-code.percentage) / 100;
  }

}
