package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
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

    private static List<Offerable> getAllAvailableOffersBySkuAndNumberOfItems(StockKeepingUnit sku, int numberOfItems) {
        List<Offerable> filteredList = SPECIAL_OFFERS.stream()
                .filter(specialOffer -> specialOffer.getSku().equals(sku) && specialOffer.getNumberOfItems() <= numberOfItems)
                .toList();
        return getSortedOffers(filteredList);
    }

    public static List<Offerable> getValidOffers( Map<StockKeepingUnit, Integer> basket) {
        Map<StockKeepingUnit, List<Offerable>> finalOffers = new EnumMap<>(StockKeepingUnit.class);
        return basket.entrySet().stream().flatMap(entry -> {
            Map<StockKeepingUnit, List<Offerable>> offers = getSpecialOffers(entry.getKey(), entry.getValue());
            for(Offerable offer : offers.get(entry.getKey())) {
                if(offer.hasNewOffer()) {
                    int count = basket.getOrDefault(offer.getOffer().getSku(), 0);
                    if(offer.getOffer().getNumberOfItems() <= count) {
                        List<Offerable> t = finalOffers.getOrDefault(offer.getOffer().getSku(), Collections.emptyList());
                        if(!t.isEmpty()){

                        }
//                        if(t.isEmpty()){
//                            finalOffers.put(offer.getOffer().getSku(), t.get(offer.getOffer().getSku()));
//                        }

                        //int eligibleOffers = count / offer.getOffer().getNumberOfItems();
                        //Map<StockKeepingUnit, List<Offerable>> t = getSpecialOffers(offer.getOffer().getSku(), count);
                        //finalOffers.put(offer.getOffer().getSku(), t.get(offer.getOffer().getSku()));

                        //offers.get()
                    }

                } else{
                    finalOffers.put(offer.getSku(), offers.get(entry.getKey()));
                }
            }
            return finalOffers.values().stream().flatMap(List::stream);
        }).toList();
    }

    public static Map<StockKeepingUnit, List<Offerable>> getSpecialOffers(StockKeepingUnit sku, int numberOfItems) {
        Map<StockKeepingUnit, List<Offerable>> offers = new EnumMap<>(StockKeepingUnit.class);
        int missingItems = numberOfItems;
        List<Offerable> availableOffers = getAllAvailableOffersBySkuAndNumberOfItems(sku, numberOfItems);
        for(Offerable offer : availableOffers) {
            if(offer.getNumberOfItems() <= missingItems) {
                int eligibleOffers = missingItems / offer.getNumberOfItems();
                offers.put(offer.getSku(), Collections.nCopies(eligibleOffers, offer));
                missingItems -= offer.getNumberOfItems() * eligibleOffers;

                //offers.addAll(Collections.nCopies(eligibleOffers, offer));
                //missingItems -= offer.getNumberOfItems() * eligibleOffers;
//                if(missingItems == 0) {
//                    break;
//                }
            }
        }
        return offers;

    }

    private static List<Offerable> getSortedOffers(final List<Offerable> offerableList) {
        return offerableList.stream()
                .sorted((s1, s2) -> {
                    double s1Discount = calculateDiscountPercentage(PriceTable.getPrice(s1.getOffer().getSku()),
                            s1.getOffer().getNumberOfItems(), s1.getOffer().getPrice());
                    double s2Discount = calculateDiscountPercentage(PriceTable.getPrice(s2.getOffer().getSku()),
                            s2.getOffer().getNumberOfItems(), s2.getOffer().getPrice());
                    return Double.compare(s2Discount, s1Discount);
                })
                .toList();
    }

    private static Double calculateDiscountPercentage(int unitPrice, int numberOfItems, int finalSellingPrice) {
        double originalPrice = (unitPrice * numberOfItems);
        double discountPrice = originalPrice - finalSellingPrice;
        return (discountPrice / originalPrice) * 100;
    }
}




