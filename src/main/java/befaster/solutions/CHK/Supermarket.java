package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Supermarket {
    private static final Map<StockKeepingUnit, Integer> PRICES = new EnumMap<>(StockKeepingUnit.class);
    private static final Map<StockKeepingUnit, List<Offerable>> SPECIAL_OFFERS = new EnumMap<>(StockKeepingUnit.class);

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
        SPECIAL_OFFERS.put(StockKeepingUnit.E, List.of(
                new MultiOffer(StockKeepingUnit.E, 2, PRICES.get(StockKeepingUnit.E) * 2,
                new SpecialOffer(StockKeepingUnit.B, 1, 0))));
    }

    public static List<Offerable> getAllAvailableOffers( Map<StockKeepingUnit, Integer> basket) {
        List<Offerable> availableOffers = basket.entrySet().stream()
                .map(entry -> getAvailableOffersBySku(entry.getKey(), entry.getValue()))
                .flatMap(List::stream)
                .toList();
        return availableOffers.stream()
                .sorted((s1, s2) -> {
            double s1Discount = calculateDiscountPercentage(PRICES.get(s1.getOffer().getSku()), s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
            double s2Discount = calculateDiscountPercentage(PRICES.get(s2.getOffer().getSku()), s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
            return Double.compare(s2Discount, s1Discount);
        })
                .toList();
    }


    //TODO: return only offers that can be applied
    private static List<Offerable> getAvailableOffersBySku(StockKeepingUnit sku, int numberOfItems){
        List<Offerable> offers = new ArrayList<>();
        int missingItems = numberOfItems;
        List<Offerable> sortedOffers = getSortedOffers(sku);
        for(Offerable offer : sortedOffers) {
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

    private static List<Offerable> getSortedOffers(final StockKeepingUnit sku) {
        return SPECIAL_OFFERS.get(sku).stream()
                .sorted((s1, s2) -> {
                    double s1Discount = calculateDiscountPercentage(PRICES.get(s1.getOffer().getSku()), s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
                    double s2Discount = calculateDiscountPercentage(PRICES.get(s2.getOffer().getSku()), s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
                    return Double.compare(s2Discount, s1Discount);
                })
                .toList();
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
        List<Offerable> availableOffersBySku = getAvailableOffersBySku(sku, numberOfItems);
        int remainingItems = numberOfItems;
        int totalPrice = 0;
        for(Offerable specialOffer : availableOffersBySku) {
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




