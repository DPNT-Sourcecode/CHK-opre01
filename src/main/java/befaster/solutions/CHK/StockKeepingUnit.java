package befaster.solutions.CHK;

public enum StockKeepingUnit {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public static StockKeepingUnit getStockKeepingUnit(char sku) throws IllegalArgumentException {
        try {
            return StockKeepingUnit.valueOf(String.valueOf(sku));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid sku %s", sku));
        }
    }
}
