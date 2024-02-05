package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.Map;

public final class Supermarket {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);
    private static final Map<StockKeepingUnit, SpecialOffer> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnit.class);

    static {
        PRICES.put(StockKeepingUnit.A, 50);
        PRICES.put(StockKeepingUnit.B, 30);
        PRICES.put(StockKeepingUnit.C, 20);
        PRICES.put(StockKeepingUnit.D, 15);

        SPECIAL_OFFERS.put(StockKeepingUnit.A, new SpecialOffer(3, 130));
        SPECIAL_OFFERS.put(StockKeepingUnit.B, new SpecialOffer(2, 45));
    }

    public static int getTotalPriceBySku(StockKeepingUnit sku, int numberOfItems){
        int numberOfItemsAccounted = 0;
        int totalPrice = 0;
        while (numberOfItemsAccounted < numberOfItems) {
            if(SPECIAL_OFFERS.containsKey(sku)) {
                SpecialOffer specialOffer = SPECIAL_OFFERS.get(sku);
                if(numberOfItems >= specialOffer.numberOfItems()) {
                    totalPrice += specialOffer.finalSellingPrice();
                    numberOfItemsAccounted += specialOffer.numberOfItems();
                } else{
                    totalPrice += PRICES.get(sku) * numberOfItems;
                    numberOfItemsAccounted += numberOfItems;
                }
            } else{
                totalPrice += PRICES.get(sku) * numberOfItems;
                numberOfItemsAccounted += numberOfItems;
            }
        }

        return totalPrice;
    }
}






