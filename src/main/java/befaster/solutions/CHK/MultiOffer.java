package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;

public final class MultiOffer implements Offerable {
    private final StockKeepingUnit sku;
    private final int numberOfItems;
    private final int price;
    private final SpecialOffer newOffer;

    public MultiOffer(final StockKeepingUnit sku, final int numberOfItems, final int price, final SpecialOffer newOffer) {
        this.sku = sku;
        this.numberOfItems = numberOfItems;
        this.price = price;
        this.newOffer = newOffer;
    }

    @Override
    public List<StockKeepingUnit> getSkus() {
        return Collections.singletonList(sku);
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
        return true;
    }

    @Override
    public Offerable getOffer() {
        return this.newOffer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiOffer that = (MultiOffer) o;

        if (numberOfItems != that.numberOfItems) return false;
        if (price != that.price) return false;
        if (sku != that.sku) return false;
        return newOffer.equals(that.newOffer);
    }

    @Override
    public int hashCode() {
        int result = sku.hashCode();
        result = 31 * result + numberOfItems;
        result = 31 * result + price;
        result = 31 * result + newOffer.hashCode();
        return result;
    }
}
