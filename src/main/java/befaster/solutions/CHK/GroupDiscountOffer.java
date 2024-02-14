package befaster.solutions.CHK;

import java.util.List;
import java.util.Set;

public class GroupDiscountOffer implements Offerable{
    private final Set<StockKeepingUnit> skus;
    private final int numberOfItems;
    private final int price;


    public GroupDiscountOffer(final Set<StockKeepingUnit> skus, final int numberOfItems, final int price) {
        this.skus = skus;
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

    @Override
    public StockKeepingUnit getSku() {
        return this.skus;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getNumberOfItems() {
        return 0;
    }

    @Override
    public boolean hasNewOffer() {
        return false;
    }

    @Override
    public Offerable getOffer() {
        return null;
    }
}

