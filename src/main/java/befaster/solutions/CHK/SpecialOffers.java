package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.K, 2, 120));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.N, 3, PriceTable.getPrice(StockKeepingUnit.N) * 3,
                new SpecialOffer(StockKeepingUnit.M, 1, 0)));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.P, 5, 200));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.Q, 3, 80));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.R, 3, PriceTable.getPrice(StockKeepingUnit.R) * 3,
                new SpecialOffer(StockKeepingUnit.Q, 1, 0)));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.U, 4, PriceTable.getPrice(StockKeepingUnit.U) * 3));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.V, 2, 90));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.V, 3, 130));
        SPECIAL_OFFERS.add(new GroupDiscountOffer(List.of(StockKeepingUnit.S, StockKeepingUnit.T, StockKeepingUnit.X, StockKeepingUnit.Y, StockKeepingUnit.Z), 3, 45));
    }

    private SpecialOffers() {

    }

    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems) {
        List<Offerable> filteredList = SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSkus().size() == 1 && specialOffer.getSkus().get(0).equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems)
                .toList();

        return sortByBestDiscount(filteredList);
    }

    private static List<Offerable> sortByBestDiscount(List<Offerable> offerableList) {
        return offerableList.stream()
                .sorted((offer1, offer2) -> {
                    double offer1Discount = calculateDiscountPercentage(getPrice(offer1),
                            offer1.getOffer().getNumberOfItems(), offer1.getOffer().getPrice());
                    double offer2Discount = calculateDiscountPercentage(getPrice(offer2),
                            offer2.getOffer().getNumberOfItems(), offer2.getOffer().getPrice());
                    return Double.compare(offer2Discount, offer1Discount);
                }).toList();
    }

    private static int getPrice(final Offerable offer) {
        return offer.getSkus()
                .stream()
                .mapToInt(PriceTable::getPrice)
                .reduce(0, Integer::sum);
    }

    private static Double calculateDiscountPercentage(int unitPrice, int numberOfItems, int finalSellingPrice) {
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice / originalPrice) * 100;
    }

    private static List<Offerable> getAllEligibleOffersInTheBasketSortedByBestDiscount(Map<StockKeepingUnit, Integer> basket, List<StockKeepingUnit> stockKeepingUnitList) {
        List<Offerable> eligibleOffers = new ArrayList<>(getAllEligibleGroupDiscountOffers(stockKeepingUnitList));
        basket.forEach((key, value) -> eligibleOffers.addAll(getEligibleOffers(key, value)));
        return sortByBestDiscount(eligibleOffers);
    }

    public static List<Offerable> updateBasketCountAndGetValidOffers(Map<StockKeepingUnit, Integer> basket, List<StockKeepingUnit> stockKeepingUnitList) {
        List<Offerable> finalOffers = new ArrayList<>();

        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket, stockKeepingUnitList).forEach(offer -> {
            if (isOfferApplicable(offer, basket)) {
                updateBasketForOffer(offer, basket);
                finalOffers.add(offer);
            }
        });

        return finalOffers;
    }

    private static boolean isOfferApplicable(Offerable offer, Map<StockKeepingUnit, Integer> basket) {
        List<StockKeepingUnit> offerSkus = offer.getSkus();
        if (offerSkus.size() > 1) {
            return offerSkus.stream().allMatch(sku -> basket.getOrDefault(sku, 0) > 0);
        }
        StockKeepingUnit sku = offerSkus.get(0);
        int skuQuantity = basket.getOrDefault(sku, 0);
        return skuQuantity >= offer.getNumberOfItems();
    }

    private static void updateBasketForOffer(Offerable offer, Map<StockKeepingUnit, Integer> basket) {
        List<StockKeepingUnit> offerSkus = offer.getSkus();
        if (offerSkus.size() > 1) {
            offerSkus.forEach(sku -> {
                int updatedQuantity = basket.get(sku) - 1;
                basket.put(sku, updatedQuantity);
            });
        } else {
            StockKeepingUnit sku = offerSkus.get(0);
            int updatedQuantity = basket.get(sku) - offer.getNumberOfItems();
            basket.put(sku, updatedQuantity);
        }
    }

    private static List<Offerable> getEligibleOffers(StockKeepingUnit sku, int numberOfItems) {
        List<Offerable> offers = new ArrayList<>();
        int missingItems = numberOfItems;
        List<Offerable> availableOffers = getAllAvailableOffersBySkuAndNumberOfItems(sku, numberOfItems);

        for (Offerable offer : availableOffers) {
            int offerItems = offer.getNumberOfItems();
            if (offerItems <= missingItems) {
                int eligibleOffers = missingItems / offerItems;
                missingItems -= offerItems * eligibleOffers;
                offers.addAll(Collections.nCopies(eligibleOffers, offer));
                if (offer.hasNewOffer()) {
                    offers.addAll(Collections.nCopies(eligibleOffers, offer.getOffer()));
                }
            }
        }
        return offers;
    }

    private static List<Offerable> getAllEligibleGroupDiscountOffers(List<StockKeepingUnit> skus) {
        List<Offerable> filteredList = new ArrayList<>();

        List<Offerable> groupDiscountOffers = SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSkus().size() > 1)
                .toList();

        groupDiscountOffers.forEach(specialOffer -> {
            List<StockKeepingUnit> skusWithGroupDiscountOffer = new ArrayList<>(skus);
            skusWithGroupDiscountOffer.retainAll(specialOffer.getSkus());

            int numberOfItems = specialOffer.getNumberOfItems();

            if (skusWithGroupDiscountOffer.size() >= numberOfItems) {
                if (skusWithGroupDiscountOffer.size() % numberOfItems != 0) {
                    skusWithGroupDiscountOffer.sort(Comparator.comparingInt(PriceTable::getPrice).reversed());
                }

                int eligibleOffers = skusWithGroupDiscountOffer.size() / numberOfItems;
                int startIndex = 0;
                int endIndex = numberOfItems;

                for (int count = 0; count < eligibleOffers; count++) {
                    List<StockKeepingUnit> skusSubList = skusWithGroupDiscountOffer.subList(startIndex, endIndex);
                    Offerable offer = new GroupDiscountOffer(skusSubList, numberOfItems, specialOffer.getPrice());
                    filteredList.add(offer);
                    startIndex = endIndex;
                    endIndex += numberOfItems;
                }
            }
        });

        return filteredList;
    }

}
