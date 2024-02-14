package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class SpecialOffers {
    private static final List<Offerable> SPECIAL_OFFERS = new ArrayList<>();

    static {
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A, 3, 130));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A, 5, 200));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.B, 2, 45));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.E, 2, PriceTable.getPrice(StockKeepingUnit.E) * 2,
                new SpecialOffer(StockKeepingUnit.B, 1, 0)));
    }

    private SpecialOffers() {

    }

    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems) {
        List<Offerable> filteredList =  SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSku().equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems)
                .toList();

        return sortByBestDiscount(filteredList);
    }

    private static List<Offerable> sortByBestDiscount(List<Offerable> offerableList){
        return offerableList.stream()
                .sorted((s1, s2) -> {
            double s1Discount = calculateDiscountPercentage(PriceTable.getPrice(s1.getOffer().getSku()),
                    s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
            double s2Discount = calculateDiscountPercentage(PriceTable.getPrice(s2.getOffer().getSku()),
                    s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
            return Double.compare(s2Discount, s1Discount);
        }).toList();
    }

    private static Double calculateDiscountPercentage(int unitPrice, int numberOfItems, int finalSellingPrice) {
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice / originalPrice) * 100;
    }

    private static List<Offerable> getAllEligibleOffersInTheBasketSortedByBestDiscount(Map<StockKeepingUnit, Integer> basket){
        List<Offerable> eligibleOffers = new ArrayList<>();
        basket.forEach((key, value) -> eligibleOffers.addAll(getEligibleOffers(key, value)));
        return sortByBestDiscount(eligibleOffers);
    }

    public static List<Offerable> updateBasketCountAndGetValidOffers(Map<StockKeepingUnit, Integer> basket) {
        List<Offerable> finalOffers = new ArrayList<>();
        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket).forEach(offer -> {
            StockKeepingUnit sku = offer.getSku();
                int skuQuantity = basket.getOrDefault(sku, 0);
                if (skuQuantity >= offer.getNumberOfItems()) {
                    int updatedQuantity = basket.get(sku) - offer.getNumberOfItems();
                    basket.put(sku, updatedQuantity);
                    finalOffers.add(offer);
                }
        });
        return finalOffers;
    }

    private static List<Offerable> getEligibleOffers(StockKeepingUnit sku, int numberOfItems) {
        List<Offerable> offers = new ArrayList<>();
        int missingItems = numberOfItems;
        List<Offerable> availableOffers = getAllAvailableOffersBySkuAndNumberOfItems(sku, numberOfItems);
        for (Offerable offer : availableOffers) {
            if (offer.getNumberOfItems() <= missingItems) {
                int eligibleOffers = missingItems / offer.getNumberOfItems();
                missingItems -= offer.getNumberOfItems() * eligibleOffers;
                offers.addAll(Collections.nCopies(eligibleOffers, offer));
                if (offer.hasNewOffer()) {
                    offers.addAll(Collections.nCopies(eligibleOffers, offer.getOffer()));
                }
            }
        }
        return offers;

    }
}

