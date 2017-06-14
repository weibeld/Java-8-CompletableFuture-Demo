package org.weibeld.bestprice;

import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

public class Shop {

  private String mName; 

  public Shop(String name) {
    mName = name;
  }

  public Future<String> getPriceAsync(String product) {
    return CompletableFuture.supplyAsync(() -> getPrice(product));
  }

  public String getPrice(String product) {
    double price = calculatePrice(product);
    Discount.Code disc = calculateDiscountCode(product);
    return String.format("%s:%.2f:%s", mName, price, disc);
  }

  // Deterministically generate a price based on shop name and product name
  private double calculatePrice(String product) {
    delay();
    if (product.equals("NA"))
      throw new IllegalArgumentException("Product not available");
    // Generate price between 0 and 199.99
    int hash = (mName + product).hashCode();
    int dollars = hash % 200;
    int cents = (hash/7) % 100;
    return Math.abs(dollars + cents/100.0);
  }

  // Deterministically pick a discount code based on shop name and product name
  private Discount.Code calculateDiscountCode(String product) {
    Discount.Code[] codes = Discount.Code.values();
    return codes[Math.abs((mName + product).hashCode()) % codes.length];
  }

  public String getName() {
    return mName;
  }

  public static void delay() {
    try {
      Thread.sleep(4000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
