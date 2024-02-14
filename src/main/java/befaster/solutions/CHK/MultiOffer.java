package befaster.solutions.CHK;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MultiOffer implements Offerable {
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
    public StockKeepingUnit getSku() {
        return this.sku;
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
    public boolean hasDiscountGroups(final Set<StockKeepingUnit> stockKeepingUnitList) {
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



