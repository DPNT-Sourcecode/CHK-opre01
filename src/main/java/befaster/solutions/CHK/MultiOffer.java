package befaster.solutions.CHK;

public record MultiOffer(StockKeepingUnit sku, SpecialOffer specialOffer) implements Offerable{

    @Override
    public int getNumberOfItems() {
        return this.specialOffer.getNumberOfItems();
    }

    @Override
    public int getPrice() {
        return this.specialOffer.getPrice();
    }
}
