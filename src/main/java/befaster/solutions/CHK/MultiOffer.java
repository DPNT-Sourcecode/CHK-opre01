package befaster.solutions.CHK;

public record MultiOffer(StockKeepingUnit sku, SpecialOffer specialOffer) implements Offerable{
    @Override
    public SpecialOffer getFinalOffer() {
        return this.specialOffer;
    }
}


