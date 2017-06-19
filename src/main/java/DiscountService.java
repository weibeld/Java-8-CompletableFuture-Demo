package org.weibeld.bestprice;

import java.util.concurrent.CompletableFuture;

/* Server
 * API:
 *   static ShopPrice applyDiscount      (Quote quote)
 *   static CompletableFuture<ShopPrice> applyDiscountAsync (Quote quote) */
public class DiscountService {

  public enum DiscountCode {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
    private final int percentage;
    DiscountCode(int percentage) {
      this.percentage = percentage;
    }
  }

  public static ShopPrice applyDiscount(Quote quote) {
    double discountedPrice = apply(quote.getPrice(), quote.getDiscountCode());
    Util.delay(2000L);
    return new ShopPrice(quote.getShopName(), discountedPrice, quote.getCurrency());
  }

  public static CompletableFuture<ShopPrice> applyDiscountAsync(Quote quote) {
    return CompletableFuture.supplyAsync(() -> applyDiscount(quote));
  }

  private static double apply(double price, DiscountCode code) {
    return price * (100-code.percentage) / 100;
  }

}
