package befaster.solutions.CHK;

public enum StockKeepingUnit {
    A, B, C, D, E;

    public static StockKeepingUnit getStockKeepingUnit(char sku) throws IllegalArgumentException {
        try {
            return StockKeepingUnit.valueOf(String.valueOf(sku));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid sku %s", sku));
        }
    }
}

