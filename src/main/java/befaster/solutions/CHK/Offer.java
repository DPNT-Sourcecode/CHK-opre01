package befaster.solutions.CHK;

import java.util.List;

public class Offer {
    private final StockKeepingUnit item;
    private final int price;
    private final List<SpecialOffer> specialOffers;

    public Offer(final StockKeepingUnit item, final int price, final List<SpecialOffer> specialOffers) {
        this.item = item;
        this.price = price;
        this.specialOffers = specialOffers;
    }
}
