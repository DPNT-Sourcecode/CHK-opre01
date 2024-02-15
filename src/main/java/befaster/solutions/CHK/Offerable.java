package befaster.solutions.CHK;

import java.util.List;

public interface Offerable {
    List<StockKeepingUnit> getSkus();

    int getPrice();

    int getNumberOfItems();

    boolean hasNewOffer();

    Offerable getOffer();

}
