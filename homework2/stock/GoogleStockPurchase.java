package homework2.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class GoogleStockPurchase implements StockPurchase {
    private final String ticker = "GOOG";
    private int quantity;
    private LocalDateTime purchaseTimestamp;
    private double purchasePricePerUnit;

    public GoogleStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit) {
        this.quantity = quantity;
        this.purchaseTimestamp = purchaseTimestamp;
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public LocalDateTime getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    @Override
    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    @Override
    public double getTotalPurchasePrice() {
        return BigDecimal.valueOf(quantity*purchasePricePerUnit).
                setScale(2, RoundingMode.HALF_UP).
                doubleValue();
    }

    @Override
    public String getStockTicker() {
        return ticker;
    }
    
}
