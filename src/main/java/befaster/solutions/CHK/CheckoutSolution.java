package befaster.solutions.CHK;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (Objects.isNull(skus) || skus.isEmpty()) {
            return 0; // Empty basket, total price is zero
        }

        final Map<StockKeepingUnit, Integer> basketCount = new LinkedHashMap<>();
        final List<StockKeepingUnit> stockKeepingUnitList;
        // Count the occurrences of each item
        try {
            stockKeepingUnitList = skus.chars()
                    .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
                    .toList();

            stockKeepingUnitList.forEach(sku -> basketCount.merge(sku, 1, Integer::sum));

        } catch (IllegalArgumentException e) {
            return -1; // Illegal input, unknown item
        }

        final List<Offerable> validOffers = SpecialOffers.updateBasketCountAndGetValidOffers(basketCount, stockKeepingUnitList);

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
