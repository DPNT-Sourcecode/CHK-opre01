package befaster.solutions.CHK;

public record SpecialOffer(int numberOfItems, int price) implements Offerable{
    @Override
    public int getNumberOfItems() {
        return this.numberOfItems;
    }
    @Override
    public int getPrice() {
        return this.price;
    }
}




