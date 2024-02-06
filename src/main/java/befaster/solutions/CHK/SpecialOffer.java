package befaster.solutions.CHK;

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
}

