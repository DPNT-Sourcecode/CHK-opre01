package befaster.solutions.CHK;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (inputIsNotValid(skus)) {
            return 0; // Empty basket, total price is zero
        }

        final List<StockKeepingUnit> stockKeepingUnitList = mapToStockKeepingUnit(skus);

        if (Objects.isNull(stockKeepingUnitList)) {
            return -1; // Illegal input, unknown item
        }

        final Map<StockKeepingUnit, Integer> basketCount =  countNumberOfSameStockKeepingUnits(stockKeepingUnitList);
        int dicountPrice = getDicountPrice(basketCount, stockKeepingUnitList);

        return sumTotal(basketCount, dicountPrice);
    }

    private static int sumTotal(final Map<StockKeepingUnit, Integer> basketCount, final int dicountPrice) {
        return basketCount.entrySet()
                .stream()
                .mapToInt(entry -> PriceTable.getPrice(entry.getKey()) * entry.getValue())
                .reduce(dicountPrice, Integer::sum);
    }

    private static int getDicountPrice(final Map<StockKeepingUnit, Integer> basketCount,
                                       final List<StockKeepingUnit> stockKeepingUnitList) {
        final List<Offerable> validOffers = SpecialOffers.updateBasketCountAndGetValidOffers(basketCount, stockKeepingUnitList);

        return validOffers
                .stream()
                .mapToInt(Offerable::getPrice)
                .reduce(0, Integer::sum);
    }

    private static Map<StockKeepingUnit, Integer> countNumberOfSameStockKeepingUnits(final List<StockKeepingUnit> stockKeepingUnitList) {
        final Map<StockKeepingUnit, Integer> basketCount = new LinkedHashMap<>();
        stockKeepingUnitList.forEach(sku -> basketCount.merge(sku, 1, Integer::sum));
        return basketCount;
    }

    private static boolean inputIsNotValid(final String skus) {
        return Objects.isNull(skus) || skus.isEmpty();
    }

    private static List<StockKeepingUnit> mapToStockKeepingUnit(final String skus) {
        try {
            return skus.chars()
                    .mapToObj(sku -> StockKeepingUnit.getStockKeepingUnit((char) sku))
                    .toList();

        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
