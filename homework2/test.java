package homework2;
import homework2.price.PriceChart;
import homework2.price.PriceChartAPI;
import homework2.stock.StockPurchase;

public class test {
    public static void main(String[] args) {
        PortfolioAPI portfolio;
        PriceChartAPI priceChart;

        priceChart = new PriceChart(100.0, 200.0, 300.0);
        portfolio = new Portfolio("John Doe", priceChart, 1000.0, 10);

        StockPurchase purchase = portfolio.buyStock("MSFT", 5);
        System.out.println(purchase.getStockTicker()); //MSFT
        System.out.println(purchase.getQuantity()); //5
        System.out.println(purchase.getPurchasePricePerUnit()); //105
        System.out.println(purchase.getTotalPurchasePrice()); //525
        System.out.println(portfolio.getRemainingBudget()); //500

        StockPurchase purchase1 = portfolio.buyStock("MSFT", 5);
        StockPurchase purchase2 = portfolio.buyStock("AMZ", 3);
        StockPurchase[] purchases = portfolio.getAllPurchases();
        System.out.println(purchases.length); //2

        StockPurchase[] purchasesInInterval = portfolio.getAllPurchases(
                purchase1.getPurchaseTimestamp(),
                purchase2.getPurchaseTimestamp().plusMinutes(1)
        );
        System.out.println(purchasesInInterval.length); //1

        double netWorth = portfolio.getNetWorth();
        System.out.println(netWorth); //1425

        System.out.println(priceChart.changeStockPrice("MSFT", 10)); //true
        System.out.println(priceChart.getCurrentPrice("MSFT")); //110
    }
}