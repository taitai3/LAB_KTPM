package iuh.fit.demo.observer;

/**
 * Concrete Subject - Stock
 * Thông báo khi giá cổ phiếu thay đổi
 */
public class Stock extends Subject {
    private String symbol;
    private double price;
    
    public Stock(String symbol, double initialPrice) {
        super();
        this.symbol = symbol;
        this.price = initialPrice;
    }
    
    public void setPrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        
        String changeType = newPrice > oldPrice ? "[TANG]" : "[GIAM]";
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        
        String message = String.format(
            "Co phieu %s: %s tu %.2f -> %.2f (%.2f%%)",
            symbol, changeType, oldPrice, newPrice, changePercent
        );
        
        notifyObservers(message);
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public double getPrice() {
        return price;
    }
}