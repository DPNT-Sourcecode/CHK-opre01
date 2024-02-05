package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.Map;

public final class Supermarket {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);
    private static final Map<StockKeepingUnit, Offerable> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnit.class);

    static {
        PRICES.put(StockKeepingUnit.A, 50);
        PRICES.put(StockKeepingUnit.B, 30);
        PRICES.put(StockKeepingUnit.C, 20);
        PRICES.put(StockKeepingUnit.D, 15);

        SPECIAL_OFFERS.put(StockKeepingUnit.A, new SpecialOffer(3, 130));
        SPECIAL_OFFERS.put(StockKeepingUnit.B, new SpecialOffer(2, 45));
    }

    public static int getTotalPriceBySku(StockKeepingUnit sku, int numberOfItems) {
        if (!SPECIAL_OFFERS.containsKey(sku)) {
            return PRICES.get(sku) * numberOfItems;
        }
        int totalPrice = 0;
        Offerable specialOffer = SPECIAL_OFFERS.get(sku);

        if (numberOfItems >= specialOffer.numberOfItems()) {
            int remainingItems = numberOfItems % specialOffer.numberOfItems();
            int eligibleOffers = numberOfItems / specialOffer.numberOfItems();
            totalPrice += specialOffer.finalSellingPrice() * eligibleOffers;
            if(remainingItems > 0) {
                totalPrice += PRICES.get(sku) * remainingItems;
            }
        } else {
            totalPrice += PRICES.get(sku) * numberOfItems;
        }
        return totalPrice;
    }
}
