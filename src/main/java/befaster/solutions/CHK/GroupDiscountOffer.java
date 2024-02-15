package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.List;

public class GroupDiscountOffer implements Offerable{
    private final List<StockKeepingUnit> skus;
    private final int numberOfItems;
    private final int price;

    public GroupDiscountOffer(final List<StockKeepingUnit> skus, final int numberOfItems, final int price) {
        this.skus = new ArrayList<>(skus);
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

    @Override
    public List<StockKeepingUnit> getSkus() {
        return new ArrayList<>(skus);
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    @Override
    public boolean hasNewOffer() {
        return false;
    }

    @Override
    public Offerable getOffer() {
        return this;
    }
}

