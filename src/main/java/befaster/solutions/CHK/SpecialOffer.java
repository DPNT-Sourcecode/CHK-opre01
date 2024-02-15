package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class SpecialOffer implements Offerable{
    private final List<StockKeepingUnit> skus;
    private final int numberOfItems;
    private final int specialPrice;

    public SpecialOffer(final List<StockKeepingUnit> skus, final int numberOfItems, final int specialPrice) {
        this.skus = new ArrayList<>(skus);
        this.numberOfItems = numberOfItems;
        this.specialPrice = specialPrice;
    }

    public SpecialOffer(final StockKeepingUnit sku, final int numberOfItems, final int specialPrice) {
        this(Collections.singletonList(sku), numberOfItems, specialPrice);
    }

    @Override
    public List<StockKeepingUnit> getSkus() {
        return new ArrayList<>(skus);
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

//    @Override
//    public Set<StockKeepingUnit> getDiscountGroupOffer() {
//        return Collections.emptySet();
//    }

//    @Override
//    public boolean hasDiscountGroups(final List<StockKeepingUnit> stockKeepingUnitList) {
//        return false;
//    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialOffer that = (SpecialOffer) o;

        if (numberOfItems != that.numberOfItems) return false;
        if (specialPrice != that.specialPrice) return false;
        return skus.equals(that.skus);
    }

    @Override
    public int hashCode() {
        int result = skus.hashCode();
        result = 31 * result + numberOfItems;
        result = 31 * result + specialPrice;
        return result;
    }
}
