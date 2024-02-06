package befaster.solutions.CHK;

public final class SpecialOffer implements Comparable<SpecialOffer> {
    private final int numberOfItems;
    private final int specialPrice;
    private final double discountPercentage;

    private SpecialOffer(final int numberOfItems,
                         final int specialPrice,
                         final double discountPercentage) {
        this.numberOfItems = numberOfItems;
        this.specialPrice = specialPrice;
        this.discountPercentage = discountPercentage;
    }

    public SpecialOffer(final int numberOfItems, final int specialPrice, final int unitOriginalPrice) {
        this(numberOfItems, specialPrice, calculateDiscountPercentage(numberOfItems, specialPrice, unitOriginalPrice));
    }

    public SpecialOffer(final int numberOfItems, final int specialPrice){
        this(numberOfItems, specialPrice, calculateDiscountPercentage(numberOfItems, specialPrice, 0));
    }

    private static Double calculateDiscountPercentage(int numberOfItems, int specialPrice, int unitOriginalPrice) {
        double originalPrice = (unitOriginalPrice * numberOfItems);
        double discountPrice = originalPrice - specialPrice;
        return (discountPrice / originalPrice) * 100;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    @Override
    public int compareTo(final SpecialOffer o) {
        return Double.compare(this.discountPercentage, o.discountPercentage);
    }
}
