package befaster.solutions.CHK;

public class MultiOffer {
    private final int numberOfItems;
    private final int unitOriginalPrice;
    private final double discountPercentage;

    private final SpecialOffer specialOffer;

    private MultiOffer(final int numberOfItems, final int unitOriginalPrice, final double discountPercentage) {
        this.numberOfItems = numberOfItems;
        this.unitOriginalPrice = unitOriginalPrice;
        this.discountPercentage = discountPercentage;
    }

    public MultiOffer(final int numberOfItems, final int unitOriginalPrice){

    }

    private static Double calculateDiscountPercentage(int numberOfItems, int specialPrice, int unitOriginalPrice) {
        double originalPrice = (unitOriginalPrice * numberOfItems);
        double discountPrice = originalPrice - specialPrice;
        return (discountPrice / originalPrice) * 100;
    }
}
