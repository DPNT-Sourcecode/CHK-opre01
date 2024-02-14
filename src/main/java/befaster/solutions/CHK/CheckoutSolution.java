package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (Objects.isNull(skus) || skus.isEmpty()) {
            return 0; // Empty basket, total price is zero
        }

        Map<StockKeepingUnit, Integer> basketCount = new LinkedHashMap<>();
        // Count the occurrences of each item
        try {
            skus.chars()
                    .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
                    .forEach(sku -> basketCount.merge(sku, 1, Integer::sum));

            basketCount.entrySet().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            return -1; // Illegal input, unknown item
        }

        //List<List<Offerable>> t = Supermarket.getAllAvailableOffers(basketCount);

        SpecialOffers.getValidOffers(basketCount);

        // Calculate total price based on prices and special offers
//        return basketCount.entrySet().stream()
//                .mapToInt(entry -> Supermarket.getTotalPriceBySku(entry.getKey(),entry.getValue()))
//                .reduce(0, Integer::sum);

        return -1;
    }

}


