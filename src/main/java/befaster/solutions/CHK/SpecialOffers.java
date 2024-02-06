package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.List;

public class SpecialOffers {
    private static final List<Offerable> SPECIAL_OFFERS = new ArrayList<>();

    static {
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A,3, 130));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.A,5, 200));
        SPECIAL_OFFERS.add(new SpecialOffer(StockKeepingUnit.B,2, 45));
        SPECIAL_OFFERS.add(new MultiOffer(StockKeepingUnit.E, 2, PRICES.get(StockKeepingUnit.E) * 2,
                new SpecialOffer(StockKeepingUnit.B, 1, 0)));
}

