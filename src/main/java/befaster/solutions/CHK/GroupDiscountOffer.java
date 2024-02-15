package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.List;

public final class GroupDiscountOffer implements Offerable{
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDiscountOffer that = (GroupDiscountOffer) o;

        if (numberOfItems != that.numberOfItems) return false;
        if (price != that.price) return false;
        return skus.equals(that.skus);
    }

    @Override
    public int hashCode() {
        int result = skus.hashCode();
        result = 31 * result + numberOfItems;
        result = 31 * result + price;
        return result;
    }
}



