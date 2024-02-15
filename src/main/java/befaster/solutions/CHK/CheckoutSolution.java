package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (Objects.isNull(skus) || skus.isEmpty()) {
            return 0; // Empty basket, total price is zero
        }

        Map<StockKeepingUnit, Integer> basketCount = new LinkedHashMap<>();
        List<StockKeepingUnit> stockKeepingUnitList;
        // Count the occurrences of each item
        try {
            stockKeepingUnitList = skus.chars()
                    .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
                    .toList();

             stockKeepingUnitList.forEach(sku -> basketCount.merge(sku, 1, Integer::sum));

        } catch (IllegalArgumentException e) {
            return -1; // Illegal input, unknown item
        }

//        List<StockKeepingUnit> stockKeepingUnitList = skus.chars()
//                .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
//                //.sorted(Comparator.comparingInt(PriceTable::getPrice).reversed())
//                .toList();

//        if (SpecialOffers.hasGroupDiscountOffers(stockKeepingUnitList)) {
//            List<Offerable> groupDiscountOffers = SpecialOffers.getGroupDiscountOffers(stockKeepingUnitList);
//
//            groupDiscountOffers.forEach(System.out::println);
//
//        }

        //SpecialOffers.getGroupDiscountOffers(stockKeepingUnitList);

        //SpecialOffers.getAllAvailableGroupDiscountOffers(stockKeepingUnitList);

        List<Offerable> validOffers = SpecialOffers.updateBasketCountAndGetValidOffers(basketCount);

        int dicountPrice = validOffers
                .stream()
                .mapToInt(Offerable::getPrice)
                .reduce(0, Integer::sum);

        return basketCount.entrySet()
                .stream()
                .mapToInt(entry -> PriceTable.getPrice(entry.getKey()) * entry.getValue())
                .reduce(dicountPrice, Integer::sum);
    }

}


