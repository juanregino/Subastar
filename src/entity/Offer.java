package entity;

public class Offer {

    private String id;

    private double amount;

    private String buyerId;

    private String productId;

    public Offer() {
    }

    public Offer(String id, double amount, String buyerId, String productId) {
        this.id = id;
        this.amount = amount;
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", buyerId='" + buyerId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
