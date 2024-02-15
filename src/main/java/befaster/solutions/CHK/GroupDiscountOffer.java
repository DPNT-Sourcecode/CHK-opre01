package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupDiscountOffer implements Offerable{
    private final StockKeepingUnit sku;
    private final Set<StockKeepingUnit> groupDiscountSkus;
    private final int numberOfItems;
    private final int price;

    public GroupDiscountOffer(final StockKeepingUnit sku, final Set<StockKeepingUnit> groupDiscountSkus, final int numberOfItems, final int price) {
        this.sku = sku;
        this.groupDiscountSkus = new HashSet<>(groupDiscountSkus);
        this.numberOfItems = numberOfItems;
        this.price = price;
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
        return false;
    }

    @Override
    public Offerable getOffer() {
        return this;
    }

    @Override
    public Set<StockKeepingUnit> getDiscountGroupOffer() {
        return new HashSet<>(this.groupDiscountSkus);
    }

//    @Override
//    public boolean hasDiscountGroups(final List<StockKeepingUnit> stockKeepingUnitList) {
//        return stockKeepingUnitList.stream().filter(groupDiscountSkus::contains).count() >= this.numberOfItems;
//    }

//    @Override
//    public List<StockKeepingUnit> getGroupDiscountOffers(List<StockKeepingUnit> skus) {
//        return skus.stream().filter(groupDiscountSkus::contains).toList();
//    }
}

