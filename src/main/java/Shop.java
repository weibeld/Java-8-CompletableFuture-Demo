package org.weibeld.bestprice;

import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;


/* Server
 * API:
 *   String         getPrice      (String product)
 *   Future<String> getPriceAsync (String product) */
public class Shop {

  private static final long DELAY = 2000L;

  private String mName; 

  public Shop(String name) {
    mName = name;
  }

  public Future<String> getPriceAsync(String product) {
    return CompletableFuture.supplyAsync(() -> getPrice(product));
  }

  public String getPrice(String product) {
    double price = determinePrice(product);
    DiscountService.Code discountCode = determineDiscountCode(product);
    Util.delay(DELAY);
    return String.format("%s:%.2f:%s", mName, price, discountCode);
  }

  // Deterministically generate a price based on shop name and product name
  private double determinePrice(String product) {
    if (product.equals("NA"))
      throw new IllegalArgumentException("Product not available");
    // Generate price between 0 and 199.99
    int hash = (mName + product).hashCode();
    int dollars = hash % 200;
    int cents = (hash/7) % 100;
    return Math.abs(dollars + cents/100.0);
  }

  // Deterministically pick a discount code based on shop name and product name
  private DiscountService.Code determineDiscountCode(String product) {
    DiscountService.Code[] codes = DiscountService.Code.values();
    return codes[Math.abs((mName + product).hashCode()) % codes.length];
  }

  public String getName() {
    return mName;
  }

}
