package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class Supermarket {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);
    private static final Map<StockKeepingUnit, List<Offerable>> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnit.class);

    static {
        PRICES.put(StockKeepingUnit.A, 50);
        PRICES.put(StockKeepingUnit.B, 30);
        PRICES.put(StockKeepingUnit.C, 20);
        PRICES.put(StockKeepingUnit.D, 15);

        SPECIAL_OFFERS.put(StockKeepingUnit.A, List.of(new SpecialOffer(3, 130), new SpecialOffer(5,200)));
        SPECIAL_OFFERS.put(StockKeepingUnit.B, List.of(new SpecialOffer(2, 45)));
    }

    //TODO: return only offers that can be applied
    private static List<Offerable> getAvailableOffersBySku(StockKeepingUnit sku, int numberOfItems){
        List<Offerable> sortedOffers = new ArrayList<>(SPECIAL_OFFERS.get(sku)).stream()
                .sorted((Comparator.comparingInt(Offerable::getNumberOfItems).reversed()))
                .toList();

        List<Offerable> offers = new ArrayList<>();
        AtomicInteger missingItems = new AtomicInteger(numberOfItems);
        sortedOffers.forEach(offer -> {
           if(offer.getNumberOfItems() <= missingItems.get()) {
               int eligibleOffers = missingItems.get() / offer.getNumberOfItems();
               offers.addAll(Collections.nCopies(eligibleOffers, offer));
               missingItems.addAndGet(-(offer.getNumberOfItems() * eligibleOffers));
           }
       });

        return offers;
    }

    public static int getTotalPriceBySku(StockKeepingUnit sku, int numberOfItems) {
        if (!SPECIAL_OFFERS.containsKey(sku)) {
            return PRICES.get(sku) * numberOfItems;
        }
        List<Offerable> availableOffersBySku = getAvailableOffersBySku(sku, numberOfItems);
        int missingItems = numberOfItems;
        int totalPrice = 0;
        for(Offerable specialOffer : availableOffersBySku) {
            totalPrice += specialOffer.getPrice();
            missingItems -= specialOffer.getNumberOfItems();
            int remainingItems = numberOfItems % specialOffer.getNumberOfItems();
            boolean t = remainingItems > 0;
//            if (numberOfItems >= specialOffer.getNumberOfItems()) {
//                int remainingItems = numberOfItems % specialOffer.getNumberOfItems();
//                int eligibleOffers = numberOfItems / specialOffer.getNumberOfItems();
//                totalPrice += specialOffer.getPrice() * eligibleOffers;
//                if(remainingItems > 0) {
//                    totalPrice += PRICES.get(sku) * remainingItems;
//                }
//            } else {
//                totalPrice += PRICES.get(sku) * numberOfItems;
//            }
        }
        return totalPrice;
    }

//    public static int getTotalPriceBySku(StockKeepingUnit sku, int numberOfItems) {
//        if (!SPECIAL_OFFERS.containsKey(sku)) {
//            return PRICES.get(sku) * numberOfItems;
//        }
//        List<Offerable> availableOffersBySku = getAvailableOffersBySku(sku, numberOfItems);
//        int totalPrice = 0;
//        for(Offerable specialOffer : availableOffersBySku) {
//            if (numberOfItems >= specialOffer.getNumberOfItems()) {
//                int remainingItems = numberOfItems % specialOffer.getNumberOfItems();
//                int eligibleOffers = numberOfItems / specialOffer.getNumberOfItems();
//                totalPrice += specialOffer.getPrice() * eligibleOffers;
//                if(remainingItems > 0) {
//                    totalPrice += PRICES.get(sku) * remainingItems;
//                }
//            } else {
//                totalPrice += PRICES.get(sku) * numberOfItems;
//            }
//        }
//        return totalPrice;
//    }
}

