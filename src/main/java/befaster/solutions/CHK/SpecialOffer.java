package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class SpecialOffer implements Offerable{
    private final StockKeepingUnit sku;
    private final int numberOfItems;
    private final int specialPrice;

    public SpecialOffer(final StockKeepingUnit sku, final int numberOfItems, final int specialPrice) {
        this.sku = sku;
        this.numberOfItems = numberOfItems;
        this.specialPrice = specialPrice;
    }

    @Override
    public StockKeepingUnit getSku() {
        return this.sku;
    }

    @Override
    public int getPrice() {
        return this.specialPrice;
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
    public boolean hasDiscountGroups(final List<StockKeepingUnit> stockKeepingUnitList) {
        return false;
    }

    @Override
    public List<StockKeepingUnit> getGroupDiscountOffers(final List<StockKeepingUnit> skus) {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialOffer that = (SpecialOffer) o;

        if (numberOfItems != that.numberOfItems) return false;
        if (specialPrice != that.specialPrice) return false;
        return sku == that.sku;
    }

    @Override
    public int hashCode() {
        int result = sku.hashCode();
        result = 31 * result + numberOfItems;
        result = 31 * result + specialPrice;
        return result;
    }
}

