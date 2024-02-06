package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Supermarket {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);
    private static final Map<StockKeepingUnit, List<SpecialOffer>> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnit.class);

    private static List<Offer> offers = new ArrayList<>();

    static {
        PRICES.put(StockKeepingUnit.A, 50);
        PRICES.put(StockKeepingUnit.B, 30);
        PRICES.put(StockKeepingUnit.C, 20);
        PRICES.put(StockKeepingUnit.D, 15);
        PRICES.put(StockKeepingUnit.E, 40);

        SPECIAL_OFFERS.put(StockKeepingUnit.A, List.of(
                new SpecialOffer(StockKeepingUnit.A,3, 130),
                new SpecialOffer(StockKeepingUnit.A,5,200)));
        SPECIAL_OFFERS.put(StockKeepingUnit.B, List.of(new SpecialOffer(StockKeepingUnit.B,2, 45)));
        //SPECIAL_OFFERS.put(StockKeepingUnit.E, List.of(new MultiOffer(2, PRICES.get(StockKeepingUnit.E))))
    }

    //TODO: return only offers that can be applied
    private static List<SpecialOffer> getAvailableOffersBySku(StockKeepingUnit sku, int numberOfItems){
        List<SpecialOffer> sortedOffers = new ArrayList<>(SPECIAL_OFFERS.get(sku)).stream()
                .sorted((s1, s2) -> {
                    double s1Discount = calculateDiscountPercentage(PRICES.get(s1.getSku()), s1.getNumberOfItems(), s1.getPrice());
                    double s2Discount = calculateDiscountPercentage(PRICES.get(s2.getSku()), s2.getNumberOfItems(), s2.getPrice());
                    return Double.compare(s2Discount, s1Discount);
                })
                .toList();

        List<SpecialOffer> offers = new ArrayList<>();
        int missingItems = numberOfItems;
        for(SpecialOffer offer : sortedOffers) {
            if(offer.getNumberOfItems() <= missingItems) {
                int eligibleOffers = missingItems / offer.getNumberOfItems();
                offers.addAll(Collections.nCopies(eligibleOffers, offer));
                missingItems -= offer.getNumberOfItems() * eligibleOffers;
                if(missingItems == 0) {
                    break;
                }
            }
        }

        return offers;
    }

    private static Double calculateDiscountPercentage(int unitPrice, int numberOfItems, int finalSellingPrice){
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice/originalPrice) * 100;
    }

    public static int getTotalPriceBySku(StockKeepingUnit sku, int numberOfItems) {
        if (!SPECIAL_OFFERS.containsKey(sku)) {
            return PRICES.get(sku) * numberOfItems;
        }
        List<SpecialOffer> availableOffersBySku = getAvailableOffersBySku(sku, numberOfItems);
        int remainingItems = numberOfItems;
        int totalPrice = 0;
        for(SpecialOffer specialOffer : availableOffersBySku) {
            totalPrice += specialOffer.getPrice();
            remainingItems = numberOfItems % specialOffer.getNumberOfItems();
        }
        if(remainingItems > 0){
            totalPrice += PRICES.get(sku) * remainingItems;
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


