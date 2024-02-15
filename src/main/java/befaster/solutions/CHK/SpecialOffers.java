package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        SPECIAL_OFFERS.add(new SpecialOffer(List.of(StockKeepingUnit.S, StockKeepingUnit.T, StockKeepingUnit.X, StockKeepingUnit.Y, StockKeepingUnit.Z), 3, 45));

        //Group Discount Offers
//        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.S, Set.of(StockKeepingUnit.T, StockKeepingUnit.X, StockKeepingUnit.Y, StockKeepingUnit.Z), 3, 45));
//        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.T, Set.of(StockKeepingUnit.S, StockKeepingUnit.X, StockKeepingUnit.Y, StockKeepingUnit.Z), 3, 45));
//        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.X, Set.of(StockKeepingUnit.S, StockKeepingUnit.T, StockKeepingUnit.Y, StockKeepingUnit.Z), 3, 45));
//        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.Y, Set.of(StockKeepingUnit.S, StockKeepingUnit.T, StockKeepingUnit.X, StockKeepingUnit.Z), 3, 45));
//        SPECIAL_OFFERS.add(new GroupDiscountOffer(StockKeepingUnit.Z, Set.of(StockKeepingUnit.S, StockKeepingUnit.T, StockKeepingUnit.X, StockKeepingUnit.Y), 3, 45));
    }

    private SpecialOffers() {

    }


//    public static List<Offerable> getGroupDiscountOffers(List<StockKeepingUnit> skus){
//        List<Offerable> groupDiscountOffers = SPECIAL_OFFERS
//                .stream()
//                .sorted(Comparator.comparingInt(o -> PriceTable.getPrice(o.getSku())))
//                //.filter(specialOffer -> !specialOffer.getDiscountGroupOffer().isEmpty())
//                .toList();
//
//        Set<StockKeepingUnit> skusFromgroupDiscountOffer = groupDiscountOffers
//                .stream()
//                .flatMap(i -> i.getDiscountGroupOffer().stream())
//                .collect(Collectors.toSet());
//
//        List<StockKeepingUnit> skusWithGroupDiscount = skus
//                .stream()
//                .filter(skusFromgroupDiscountOffer::contains)
//                .toList();
//
//        //if(skusWithGroupDiscount.size())
//
//        skusWithGroupDiscount.forEach(System.out::println);
//
//        return groupDiscountOffers;
//    }

//    public static List<Offerable> getAllAvailableGroupDiscountOffers(List<StockKeepingUnit> skus) {
//        List<Offerable> groupDiscountOffers = SPECIAL_OFFERS
//                .stream()
//                .filter(specialOffer -> !specialOffer.getDiscountGroupOffer().isEmpty())
//                .toList();
//
//        List<Offerable> sortedGroupDiscountOffers = sortByBestDiscount(groupDiscountOffers);
//
//        List<Offerable> filteredList = new LinkedList<>();
//        List<StockKeepingUnit> groupDiscountSkus = new LinkedList<>();
//        sortedGroupDiscountOffers.forEach(specialOffer -> {
//            if(skus.contains(specialOffer.getSku())){
//
//                System.out.println("Contains");
//            }
//        });
////        List<Offerable> filteredList = SPECIAL_OFFERS.stream()
////                .filter(specialOffer -> specialOffer.getSku().equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems)
////                .toList();
//
//        return sortByBestDiscount(filteredList);
//    }

//    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems) {
//        List<Offerable> filteredList =  SPECIAL_OFFERS.stream()
//                .filter(specialOffer -> (specialOffer.getSku().equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems) || specialOffer.getDiscountGroupOffer().contains(sku))
//                .toList();
//
//        return sortByBestDiscount(filteredList);
//    }

    //CHK_5
    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems, List<StockKeepingUnit> skus) {
        List<Offerable> filteredList = new ArrayList<>();
        SPECIAL_OFFERS.forEach(specialOffer -> {
            if(specialOffer.getSkus().contains(sku)) {
                if(specialOffer.getSkus().size() > 1) {
                    List<StockKeepingUnit> skusWithGroupDiscountOffer = skus
                            .stream()
                            .filter(specialOffer.getSkus()::contains)
                            .toList();

                    if(skusWithGroupDiscountOffer.size() % specialOffer.getNumberOfItems() == 0){
                        //Can have more than one discount group
                        

                    }

                } else if (specialOffer.getNumberOfItems() <= numberOfItems) {
                    filteredList.add(specialOffer);
                }
            }

        });


//        Set<Offerable> filteredList =  SPECIAL_OFFERS.stream()
//                .filter(specialOffer -> (specialOffer.getSkus().contains(sku) && specialOffer.getNumberOfItems() <= numberOfItems) || specialOffer.getSkus().contains(sku))
//                .collect(Collectors.toSet());

        return sortByBestDiscount(filteredList);
    }

//    //CHK_4
//    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems) {
//        List<Offerable> filteredList =  SPECIAL_OFFERS.stream()
//                .filter(specialOffer -> specialOffer.getSku().equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems)
//                .toList();
//
//        return sortByBestDiscount(filteredList);
//    }

    //CHK_5
    private static List<Offerable> sortByBestDiscount(Collection<Offerable> offerableList){
        return offerableList.stream()
                .sorted((s1, s2) -> {
                    double s1Discount = calculateDiscountPercentage(PriceTable.getPrice(s1.getOffer().getSkus().get(0)),
                            s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
                    double s2Discount = calculateDiscountPercentage(PriceTable.getPrice(s2.getOffer().getSkus().get(0)),
                            s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
                    return Double.compare(s2Discount, s1Discount);
                }).toList();
    }

//    //CHK_4
//    private static List<Offerable> sortByBestDiscount(List<Offerable> offerableList){
//        return offerableList.stream()
//                .sorted((s1, s2) -> {
//            double s1Discount = calculateDiscountPercentage(PriceTable.getPrice(s1.getOffer().getSku()),
//                    s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
//            double s2Discount = calculateDiscountPercentage(PriceTable.getPrice(s2.getOffer().getSku()),
//                    s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
//            return Double.compare(s2Discount, s1Discount);
//        }).toList();
//    }

    //CHK_4
    private static Double calculateDiscountPercentage(int unitPrice, int numberOfItems, int finalSellingPrice) {
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice / originalPrice) * 100;
    }

    //CHK_4
    private static List<Offerable> getAllEligibleOffersInTheBasketSortedByBestDiscount(Map<StockKeepingUnit, Integer> basket, List<StockKeepingUnit> skus){
        List<Offerable> eligibleOffers = new ArrayList<>();
        basket.forEach((key, value) -> eligibleOffers.addAll(getEligibleOffers(key, value, skus)));
        return sortByBestDiscount(eligibleOffers);
    }

    //CHK_5
    public static List<Offerable> updateBasketCountAndGetValidOffers(Map<StockKeepingUnit, Integer> basket, List<StockKeepingUnit> skus) {
        List<Offerable> finalOffers = new ArrayList<>();
        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket, skus).forEach(offer -> {
            StockKeepingUnit sku = offer.getSkus().get(0);
            int skuQuantity = basket.getOrDefault(sku, 0);
            if (skuQuantity >= offer.getNumberOfItems()) {
                int updatedQuantity = basket.get(sku) - offer.getNumberOfItems();
                basket.put(sku, updatedQuantity);
                finalOffers.add(offer);
            }
        });
        return finalOffers;
    }

//    //CHK_4
//    public static List<Offerable> updateBasketCountAndGetValidOffers(Map<StockKeepingUnit, Integer> basket) {
//        List<Offerable> finalOffers = new ArrayList<>();
//        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket).forEach(offer -> {
//            StockKeepingUnit sku = offer.getSku();
//                int skuQuantity = basket.getOrDefault(sku, 0);
//                if (skuQuantity >= offer.getNumberOfItems()) {
//                    int updatedQuantity = basket.get(sku) - offer.getNumberOfItems();
//                    basket.put(sku, updatedQuantity);
//                    finalOffers.add(offer);
//                }
//        });
//        return finalOffers;
//    }

//    public static List<Offerable> updateBasketCountAndGetValidOffers(Map<StockKeepingUnit, Integer> basket) {
//        List<Offerable> finalOffers = new ArrayList<>();
//        getAllEligibleOffersInTheBasketSortedByBestDiscount(basket).forEach(offer -> {
//            StockKeepingUnit sku = offer.getSku();
//            int skuQuantity = basket.getOrDefault(sku, 0);
//            List<StockKeepingUnit> availableDiscountOffers = new ArrayList<>();
//
//            if(skuQuantity >= 1 && !offer.getDiscountGroupOffer().isEmpty()){
//                availableDiscountOffers.addAll(Collections.nCopies(skuQuantity, sku));
//
//                List<StockKeepingUnit> t  = offer.getDiscountGroupOffer()
//                        .stream()
//                        .filter(s -> basket.containsKey(s))
//                        .toList();
//
//                int availableSpace =
//
//                t.forEach(System.out::println);
//            }
//
//
//            if (skuQuantity >= offer.getNumberOfItems()) {
//                int updatedQuantity = basket.get(sku) - offer.getNumberOfItems();
//                basket.put(sku, updatedQuantity);
//                finalOffers.add(offer);
//            }
//        });
//        return finalOffers;
//    }

//    private static List<Offerable> getEligibleOffers(StockKeepingUnit sku, int numberOfItems) {
//        List<Offerable> offers = new ArrayList<>();
//        int missingItems = numberOfItems;
//        List<Offerable> availableOffers = getAllAvailableOffersBySkuAndNumberOfItems(sku, numberOfItems);
//        for (Offerable offer : availableOffers) {
//            if (!offer.getDiscountGroupOffer().contains(sku) && offer.getNumberOfItems() <= missingItems) {
//                int eligibleOffers = missingItems / offer.getNumberOfItems();
//                missingItems -= offer.getNumberOfItems() * eligibleOffers;
//                offers.addAll(Collections.nCopies(eligibleOffers, offer));
//                if (offer.hasNewOffer()) {
//                    offers.addAll(Collections.nCopies(eligibleOffers, offer.getOffer()));
//                }
//            } else if(offer.getDiscountGroupOffer().contains(sku)){
//                offers.add(offer);
//            }
//        }
//        return offers;
//    }

    //CHK_4
    private static List<Offerable> getEligibleOffers(StockKeepingUnit sku, int numberOfItems, List<StockKeepingUnit> skus) {
        List<Offerable> offers = new ArrayList<>();
        int missingItems = numberOfItems;
        List<Offerable> availableOffers = getAllAvailableOffersBySkuAndNumberOfItems(sku, numberOfItems, skus);
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


