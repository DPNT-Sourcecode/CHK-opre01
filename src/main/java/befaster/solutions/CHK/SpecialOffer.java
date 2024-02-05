package befaster.solutions.CHK;

public record SpecialOffer(int numberOfItems, int finalSellingPrice) implements Offerable<SpecialOffer>{

    @Override
    public SpecialOffer getOffer() {
        return this;
    }
}

