package befaster.solutions.CHK;

public interface Offerable {

    StockKeepingUnit getSku();
    int getPrice();
    int getNumberOfItems();
    boolean hasNewOffer();
    Offerable getOffer();

}


