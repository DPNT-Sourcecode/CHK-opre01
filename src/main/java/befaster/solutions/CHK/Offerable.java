package befaster.solutions.CHK;

import java.util.List;
import java.util.Set;


public interface Offerable {

    StockKeepingUnit getSku();
    int getPrice();
    int getNumberOfItems();
    boolean hasNewOffer();
    Offerable getOffer();
    //boolean hasDiscountGroups(List<StockKeepingUnit> stockKeepingUnitList);

    Set<StockKeepingUnit> getDiscountGroupOffer();

}
