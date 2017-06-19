package org.weibeld.bestprice;

public class ExchangeService {
  
  public enum Currency {
    EUR, USD;
  }

  public static double getUsdEur() {
    Util.delay(2000L);
    return 0.8937;
  }

}
