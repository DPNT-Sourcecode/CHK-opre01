package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;

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
    public List<StockKeepingUnit> getSkus() {
        return Collections.singletonList(sku);
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

