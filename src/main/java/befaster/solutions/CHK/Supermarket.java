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

    public static int getPriceBySku(StockKeepingUnit sku, int numberOfItems){
        if(SPECIAL_OFFERS.containsKey(sku)) {
            SpecialOffer specialOffer = SPECIAL_OFFERS.get(sku);
            if(specialOffer)
        }

        return PRICES.get(sku);
    }



    public static int getSpecialOfferNumberOfItems(StockKeepingUnit sku){
        return SPECIAL_OFFERS.containsKey(sku) ? SPECIAL_OFFERS.get(sku).numberOfItems() : 1;
    }

    public static int getSpecialOfferPrice(StockKeepingUnit sku){
        return SPECIAL_OFFERS.containsKey(sku) ? SPECIAL_OFFERS.get(sku).finalSellingPrice() : 0;
    }

}

