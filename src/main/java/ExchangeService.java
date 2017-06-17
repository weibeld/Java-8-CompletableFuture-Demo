package org.weibeld.bestprice;

public class ExchangeService {
  
  public enum Currency {
    EUR, USD;
  }

  public static double getUsdEur() {
    Util.delay(500L);
    return 0.8937;
  }

}
