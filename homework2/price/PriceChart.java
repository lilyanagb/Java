package homework2.price;

public class PriceChart implements PriceChartAPI {
    private double microsoftStockPrice;
    private double googleStockPrice;
    private double amazonStockPrice;

    public PriceChart(double microsoftStockPrice, double googleStockPrice, double amazonStockPrice) {
        this.microsoftStockPrice = microsoftStockPrice;
        this.googleStockPrice = googleStockPrice;
        this.amazonStockPrice = amazonStockPrice;
    }

    @Override
    public double getCurrentPrice(String stockTicker) {
        if(stockTicker == null) return 0.0;

        switch(stockTicker){
            case "MSFT":
                return this.microsoftStockPrice;
            case "GOOG":
                return this.googleStockPrice;
            case "AMZ":
                return this.amazonStockPrice;
            default:
                return 0.0;
        } 
    }

    @Override
    public boolean changeStockPrice(String stockTicker, int percentChange) {
        if(stockTicker == null || percentChange <= 0) return false;

        double currentPrice = getCurrentPrice(stockTicker);
        double newPrice = currentPrice * (1 + percentChange / 100.0);

        if(stockTicker == "MSFT"){
            this.microsoftStockPrice = newPrice;
        }
        else if(stockTicker == "GOOG"){
            this.googleStockPrice = newPrice;
        }
        else if(stockTicker == "AMZ"){
            this.amazonStockPrice = newPrice;
        }

        return false;
    }
}
