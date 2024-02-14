package befaster.solutions.CHK;

import java.util.List;

public interface Offerable {

    StockKeepingUnit getSku();
    int getPrice();
    int getNumberOfItems();
    boolean hasNewOffer();
    Offerable getOffer();
    boolean hasDiscountGroup(List<StockKeepingUnit> stockKeepingUnitList);

}

