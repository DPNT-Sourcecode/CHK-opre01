package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (Objects.isNull(skus) || skus.isEmpty()) {
            return 0; // Empty basket, total price is zero
        }

        int total = 0;
        Map<StockKeepingUnits, Integer> skuCounts = new EnumMap<>(StockKeepingUnits.class);
        

    }
}

