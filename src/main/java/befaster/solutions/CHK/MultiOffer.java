package befaster.solutions.CHK;

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
}


