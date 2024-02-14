package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SpecialOffers {
    private static final Set<Offerable> SPECIAL_OFFERS = new HashSet<>();

    static {
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A, 3, 130));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A, 5, 200));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.B, 2, 45));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.E, 2, PriceTable.getPrice(StockKeepingUnit.E) * 2,
                new SpecialOffer(StockKeepingUnit.B, 1, 0)));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.F, 3, PriceTable.getPrice(StockKeepingUnit.F) * 2));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.H, 5, 45));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.H, 10, 80));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.K, 2, 150));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.N, 3, PriceTable.getPrice(StockKeepingUnit.N) * 3,
                new SpecialOffer(StockKeepingUnit.M, 1, 0)));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.P, 5, 200));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.Q, 3, 80));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.R, 3, PriceTable.getPrice(StockKeepingUnit.R) * 3,
                new SpecialOffer(StockKeepingUnit.Q, 1, 0)));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.U, 4, PriceTable.getPrice(StockKeepingUnit.U) * 3));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.V, 2, 90));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.V, 3, 130));

        Set<StockKeepingUnit> groupDiscountOffer = Set.of(
                StockKeepingUnit.S,
                StockKeepingUnit.T,
                StockKeepingUnit.X,
                StockKeepingUnit.Y,
                StockKeepingUnit.Z
        );
        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.S, groupDiscountOffer, 3, 45));
    }

    private SpecialOffers() {

    }

    public static boolean hasGroupDiscountOffers(Set<StockKeepingUnit> skus){
        return SPECIAL_OFFERS.stream().anyMatch(specialOffer -> specialOffer.hasDiscountGroups(skus));
    }

    public static List<StockKeepingUnit> getGroupDiscountOffers(List<StockKeepingUnit> skus){
        return SPECIAL_OFFERS.stream().flatMap(s -> s.getGroupDiscountOffers(skus).stream()).toList();
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

