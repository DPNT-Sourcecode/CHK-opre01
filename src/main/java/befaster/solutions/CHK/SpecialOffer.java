package befaster.solutions.CHK;

public record SpecialOffer(int numberOfItems, int price) implements Offerable, Comparable<SpecialOffer>{
    @Override
    public int getNumberOfItems() {
        return this.numberOfItems;
    }
    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int compareTo(final SpecialOffer o) {
        return 0;
    }
}

