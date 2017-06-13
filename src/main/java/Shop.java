import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

public class Shop {

  private String mName;

  public Shop(String name) {
    mName = name;
  }

  public Future<Double> getPriceAsync(String product) {
    CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(() -> {
      try {
        future.complete(calculatePrice(product));
      } catch (Exception e) {
        future.completeExceptionally(e);
      }
    }).start();
    return future;
  }

  public double getPrice(String product) {
    return calculatePrice(product);
  }

  private double calculatePrice(String product) {
    delay();
    if (product.equals("NA"))
      throw new IllegalArgumentException("Product not available");
    // Generate a price between 0 and 199.99 based on the product name
    int hash = product.hashCode();
    int dollars = hash % 200;
    int cents = (hash/7) % 100;
    return Math.abs(dollars + cents/100.0);
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
