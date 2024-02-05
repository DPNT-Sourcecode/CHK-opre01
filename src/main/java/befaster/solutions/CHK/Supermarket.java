package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Supermarket {

    private static final Map<StockKeepingUnits, Integer> PRICES = new EnumMap<>(StockKeepingUnits.class);
    private static final Map<StockKeepingUnits, SpecialOffer> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnits.class);

    static {
        PRICES.put(StockKeepingUnits.A, 50);
        PRICES.put(StockKeepingUnits.B, 30);
        PRICES.put(StockKeepingUnits.C, 20);
        PRICES.put(StockKeepingUnits.D, 15);

        SPECIAL_OFFERS.put(StockKeepingUnits.A, new SpecialOffer(3, 130));
        SPECIAL_OFFERS.put(StockKeepingUnits.B, new SpecialOffer(2, 45));
    }

}
