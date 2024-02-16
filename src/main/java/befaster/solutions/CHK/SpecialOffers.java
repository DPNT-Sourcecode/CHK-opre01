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

    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(final StockKeepingUnit sku, final int numberOfItems) {
        return SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSkus().size() == 1 && specialOffer.getSkus().get(0).equals(sku)
                        && specialOffer.getNumberOfItems() <= numberOfItems)
                .sorted(SpecialOffers::compareByBestDiscountReverseOrder)
                .toList();
    }

    private static int compareByBestDiscountReverseOrder(final Offerable offer1, final Offerable offer2) {
        double offer1Discount = calculateDiscountPercentage(getPrice(offer1),
                offer1.getOffer().getNumberOfItems(), offer1.getOffer().getPrice());
        double offer2Discount = calculateDiscountPercentage(getPrice(offer2),
                offer2.getOffer().getNumberOfItems(), offer2.getOffer().getPrice());
        return Double.compare(offer2Discount, offer1Discount);
    }

    private static int getPrice(final Offerable offer) {
        return offer.getSkus()
                .stream()
                .mapToInt(PriceTable::getPrice)
                .reduce(0, Integer::sum);
    }

    private static Double calculateDiscountPercentage(final int unitPrice,
                                                      final int numberOfItems,
                                                      final int finalSellingPrice) {
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice / originalPrice) * 100;
    }

    private static List<Offerable> getAllEligibleOffersInTheBasketSortedByBestDiscount(
            final Map<StockKeepingUnit, Integer> basket,
            final List<StockKeepingUnit> stockKeepingUnitList) {
        List<Offerable> eligibleOffers = new ArrayList<>(getAllEligibleGroupDiscountOffers(stockKeepingUnitList));

        basket.forEach((key, value) -> eligibleOffers.addAll(getEligibleOffers(key, value)));

        eligibleOffers.sort(SpecialOffers::compareByBestDiscountReverseOrder);

        return eligibleOffers;
    }

    public static List<Offerable> updateBasketCountAndGetValidOffers(final Map<StockKeepingUnit, Integer> basket,
                                                                     final List<StockKeepingUnit> stockKeepingUnitList) {
        List<Offerable> finalOffers = new ArrayList<>();

        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket, stockKeepingUnitList).forEach(offer -> {
            if (isOfferApplicable(offer, basket)) {
                updateBasketForOffer(offer, basket);
                finalOffers.add(offer);
            }
        });

        return finalOffers;
    }

    private static boolean isOfferApplicable(final Offerable offer, final Map<StockKeepingUnit, Integer> basket) {
        List<StockKeepingUnit> offerSkus = offer.getSkus();
        if (offerSkus.size() > 1) {
            return offerSkus.stream().allMatch(sku -> basket.getOrDefault(sku, 0) > 0);
        }
        StockKeepingUnit sku = offerSkus.get(0);
        int skuQuantity = basket.getOrDefault(sku, 0);
        return skuQuantity >= offer.getNumberOfItems();
    }

    private static void updateBasketForOffer(final Offerable offer, final Map<StockKeepingUnit, Integer> basket) {
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

    private static List<Offerable> getEligibleOffers(final StockKeepingUnit sku, final int numberOfItems) {
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

    private static List<Offerable> getAllEligibleGroupDiscountOffers(final List<StockKeepingUnit> skus) {
        return getGroupDiscountOffers().stream()
                .flatMap(specialOffer -> getEligibleGroupDiscountOffer(skus, specialOffer).stream())
                .toList();
    }

    private static List<Offerable> getEligibleGroupDiscountOffer(final List<StockKeepingUnit> skus, final Offerable specialOffer) {
        List<Offerable> filteredList = new ArrayList<>();
        List<StockKeepingUnit> skusWithGroupDiscountOffer = getSkusWithGroupDiscountOffer(skus, specialOffer.getSkus());

        int numberOfItems = specialOffer.getNumberOfItems();
        int size = skusWithGroupDiscountOffer.size();

        if (size >= numberOfItems) {
            if (size % numberOfItems != 0) {
                sortByHighestPriceDescendingOrder(skusWithGroupDiscountOffer);
            }

            int eligibleOffers = size / numberOfItems;
            int startIndex = 0;
            int endIndex = numberOfItems;
            for (int count = 0; count < eligibleOffers; count++) {
                Offerable offer = new GroupDiscountOffer(skusWithGroupDiscountOffer.subList(startIndex, endIndex),
                        numberOfItems, specialOffer.getPrice());
                filteredList.add(offer);
                startIndex = endIndex;
                endIndex += numberOfItems;
            }
        }
        return filteredList;
    }

    private static void sortByHighestPriceDescendingOrder(final List<StockKeepingUnit> skusWithGroupDiscountOffer) {
        skusWithGroupDiscountOffer
                .sort(Comparator.comparingInt(PriceTable::getPrice)
                .reversed());
    }

    private static List<StockKeepingUnit> getSkusWithGroupDiscountOffer(final List<StockKeepingUnit> skus, final List<StockKeepingUnit> offerSkus) {
        List<StockKeepingUnit> skusWithGroupDiscountOffer = new ArrayList<>(skus);
        skusWithGroupDiscountOffer.retainAll(offerSkus);
        return skusWithGroupDiscountOffer;
    }

    private static List<Offerable> getGroupDiscountOffers() {
        return SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSkus().size() > 1)
                .toList();
    }
}

