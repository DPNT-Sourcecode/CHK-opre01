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
        PRICES.put(StockKeepingUnit.F, 10);
        PRICES.put(StockKeepingUnit.G, 20);
        PRICES.put(StockKeepingUnit.H, 10);
        PRICES.put(StockKeepingUnit.I, 35);
        PRICES.put(StockKeepingUnit.J, 60);
        PRICES.put(StockKeepingUnit.K, 70);
        PRICES.put(StockKeepingUnit.L, 90);
        PRICES.put(StockKeepingUnit.M, 15);
        PRICES.put(StockKeepingUnit.N, 40);
        PRICES.put(StockKeepingUnit.O, 10);
        PRICES.put(StockKeepingUnit.P, 50);
        PRICES.put(StockKeepingUnit.Q, 30);
        PRICES.put(StockKeepingUnit.R, 50);
        PRICES.put(StockKeepingUnit.S, 20);
        PRICES.put(StockKeepingUnit.T, 20);
        PRICES.put(StockKeepingUnit.U, 40);
        PRICES.put(StockKeepingUnit.V, 50);
        PRICES.put(StockKeepingUnit.W, 20);
        PRICES.put(StockKeepingUnit.X, 17);
        PRICES.put(StockKeepingUnit.Y, 20);
        PRICES.put(StockKeepingUnit.Z, 21);
    }

    private PriceTable() {
    }

    public static int getPrice(StockKeepingUnit sku) {
        return PRICES.getOrDefault(sku, -1);
    }

}

