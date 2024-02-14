package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.Map;

public final class PriceTable {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);

    static {
        PRICES.put(StockKeepingUnit.A, 50);
        PRICES.put(StockKeepingUnit.B, 30);
        PRICES.put(StockKeepingUnit.C, 20);
        PRICES.put(StockKeepingUnit.D, 15);
        PRICES.put(StockKeepingUnit.E, 40);
    }

    public static int getPrice(StockKeepingUnit sku){
        return PRICES.getOrDefault(sku, -1);
    }

}
