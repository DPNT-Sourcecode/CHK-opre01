package befaster.solutions.CHK;

import java.util.List;
import java.util.Set;

public interface Offerable {

    StockKeepingUnit getSku();
    int getPrice();
    int getNumberOfItems();
    boolean hasNewOffer();
    Offerable getOffer();
    Set<StockKeepingUnit> getDiscountGroup(Set<StockKeepingUnit> stockKeepingUnitList);

}
