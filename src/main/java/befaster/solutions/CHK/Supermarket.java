package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class Supermarket {

    private static final Map<StockKeepingUnits, Integer> prices = new HashMap<>();
    private static final Map<Character, SpecialOffer> specialOffers = new HashMap<>();

    static {
        prices.put(StockKeepingUnits.A, 50);
        prices.put(StockKeepingUnits.B, 30);
        prices.put(StockKeepingUnits.C, 20);
        prices.put(StockKeepingUnits.D, 15);

        specialOffers.put(StockKeepingUnits.A, new SpecialOffer(3, 130));
        specialOffers.put(StockKeepingUnits.B, new SpecialOffer(2, 45));
    }

}

