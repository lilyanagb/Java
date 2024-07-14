package homework2;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.RoundingMode;

import homework2.price.PriceChartAPI;
import homework2.stock.AmazonStockPurchase;
import homework2.stock.GoogleStockPurchase;
import homework2.stock.MicrosoftStockPurchase;
import homework2.stock.StockPurchase;

public class Portfolio implements PortfolioAPI {
    private String owner;
    private PriceChartAPI priceChart;
    private StockPurchase[] stockPurchases;
    private double budget;
    private int maxSize;
    private int purchaseCount;
    
    public Portfolio(String owner, PriceChartAPI priceChart, double budget, int maxSize){
        this.owner = owner;
        this.priceChart = priceChart;
        this.stockPurchases = new StockPurchase[maxSize];
        this.budget = budget;
        this.maxSize = maxSize;
        this.purchaseCount = 0;
    }

    public Portfolio(String owner, PriceChartAPI priceChart, StockPurchase[] stockPurchases, double budget, int maxSize){
        this(owner, priceChart, budget, maxSize);
        for(StockPurchase purchase : stockPurchases){
            buyStock(purchase.getStockTicker(), purchase.getQuantity());
        }
    }

    @Override
    public StockPurchase buyStock(String stockTicker, int quantity) {
        if(stockTicker == null || quantity <= 0 || purchaseCount >= maxSize){
            return null;
        }
        
        double currentPrice = priceChart.getCurrentPrice(stockTicker);
        if(currentPrice == 0) return null;

        double totalPrice = currentPrice * quantity;
        if(totalPrice > budget) return null;

        StockPurchase purchase;
        budget -= totalPrice;

        switch(stockTicker){
            case "MSFT":
                purchase = new MicrosoftStockPurchase(quantity, LocalDateTime.now(), currentPrice);
                break;
            case "GOOG":
                purchase = new GoogleStockPurchase(quantity, LocalDateTime.now(), currentPrice);
                break;
            case "AMZ":
                purchase = new AmazonStockPurchase(quantity, LocalDateTime.now(), currentPrice);
                break;
            default:
                return null;
        }

        this.stockPurchases[purchaseCount] = purchase;
        purchaseCount++;

        priceChart.changeStockPrice(stockTicker, 5);

        return purchase;
    }

    @Override
    public StockPurchase[] getAllPurchases() {
        StockPurchase[] copy = new StockPurchase[purchaseCount];
        System.arraycopy(stockPurchases, 0, copy, 0, purchaseCount);
        return copy;
    }

    @Override
    public StockPurchase[] getAllPurchases(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        StockPurchase[] purchaseInInterval = new StockPurchase[maxSize];
        int count = 0;

        for(int i = 0; i < purchaseCount; i++){
            LocalDateTime purchaseTimestamp = stockPurchases[i].getPurchaseTimestamp();
            if(purchaseTimestamp.isEqual(startTimestamp) || 
                (purchaseTimestamp.isAfter(startTimestamp) && purchaseTimestamp.isBefore(endTimestamp))){
                    purchaseInInterval[count] = stockPurchases[i];
                    count++;
                }
        }

        StockPurchase[] copy = new StockPurchase[count];
        System.arraycopy(purchaseInInterval, 0, copy, 0, count);
        return copy;
    }

    @Override
    public double getNetWorth() {
        double netWorth = 0.0;

        for(StockPurchase purchase : stockPurchases){
            double currentPrice = priceChart.getCurrentPrice(purchase.getStockTicker());
            netWorth += (currentPrice * purchase.getQuantity());
        }

        return BigDecimal.valueOf(netWorth).
                setScale(2, RoundingMode.HALF_UP).
                doubleValue();
    }

    @Override
    public double getRemainingBudget() {
       return BigDecimal.valueOf(budget).
                setScale(2, RoundingMode.HALF_UP).
                doubleValue();
       //Math.round(budget * 100) / 100.0;
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
