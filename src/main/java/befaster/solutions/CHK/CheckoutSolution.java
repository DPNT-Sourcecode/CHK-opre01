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
            return -1; // Illegal input, unknown item
        }

        // Calculate total price based on prices and special offers
        for (Map.Entry<StockKeepingUnit, Integer> entry : basketCount.entrySet()) {
            StockKeepingUnit sku = entry.getKey();
            int count = entry.getValue();

            int price = Supermarket.getTotalPriceBySku(sku, count);
            int specialOfferCount = Supermarket.getSpecialOfferNumberOfItems(sku);
            int specialOfferPrice = Supermarket.getSpecialOfferPrice(sku);

            int regularPrice = (count % specialOfferCount) * price;
            int specialOfferPriceTotal = (count / specialOfferCount) * specialOfferPrice;

            total += regularPrice + specialOfferPriceTotal;
        }

    return total;
    }
}

