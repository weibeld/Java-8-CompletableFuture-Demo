import java.util.concurrent.Future;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {

  private List<Shop> mShops = Arrays.asList(new Shop("BestShop"),
                                            new Shop("WeRipYouOff"),
                                            new Shop("Abriss GmbH"),
                                            new Shop("IKEA"));

  public double findPrice(String shop, String product) {
    return (new Shop(shop)).getPrice(product);
  }

  public Future<Double> findPriceAsync(String shop, String product) {
    return (new Shop(shop)).getPriceAsync(product);
  }

  public List<String> findAllPrices(String product) {
    return mShops.stream()
      .map(s -> String.format("%s: %.2f", s.getName(), s.getPrice(product)))
      .collect(Collectors.toList());
  }

  public List<String> findAllPricesParallel(String product) {
    // Note that this stream is not guaranteed to be parallel
    return mShops.parallelStream()
      .map(s -> String.format("%s: %.2f", s.getName(), s.getPrice(product)))
      .collect(Collectors.toList());
  }

}
