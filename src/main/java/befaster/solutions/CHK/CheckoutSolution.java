package befaster.solutions.CHK;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (Objects.isNull(skus) || skus.isEmpty()) {
            return 0; // Empty basket, total price is zero
        }

        int total = 0;
        Map<StockKeepingUnit, Integer> basketCount = new EnumMap<>(StockKeepingUnit.class);
        // Count the occurrences of each item
        try {
            skus.chars()
                    .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
                    .forEach(sku -> basketCount.merge(sku, 1, Integer::sum));
        } catch (IllegalArgumentException e) {
            return -1;
        }

        

    return total;
    }
}



