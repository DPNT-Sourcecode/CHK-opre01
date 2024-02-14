package befaster.solutions.CHK;

import java.util.List;
import java.util.Set;

public class GroupDiscountOffer implements Offerable{
    private final StockKeepingUnit sku;
    private final Set<StockKeepingUnit> groupDiscountSkus;
    private final int numberOfItems;
    private final int price;

    public GroupDiscountOffer(final StockKeepingUnit sku, final Set<StockKeepingUnit> groupDiscountSkus, final int numberOfItems, final int price) {
        this.sku = sku;
        this.groupDiscountSkus = groupDiscountSkus;
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

//    public boolean hasDiscountGroup() {
//        groupDiscountSkus.retainAll()
//    }

    @Override
    public StockKeepingUnit getSku() {
        return null;
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
