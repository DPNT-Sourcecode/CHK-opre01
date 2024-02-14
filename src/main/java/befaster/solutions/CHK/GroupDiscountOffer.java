package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.HashSet;
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
    public List<StockKeepingUnit> getDiscountGroups(List<StockKeepingUnit> skus) {
        List<StockKeepingUnit> groupDiscountOfferFound = new ArrayList<>();
        skus.forEach(s -> {
            if(groupDiscountSkus.contains(s)){
                groupDiscountOfferFound.add(s);
            }
        });
        return groupDiscountOfferFound;
    }
}

