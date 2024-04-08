package entity;

public class Product {

    private String id;

    private String name;
    private String description;

    private double initialPrice;

    private double finalPrice;

    private String sellerId;

    private State state;


    public Product() {
    }

    public Product(String id, String name, String description, double initialPrice, double finalPrice, String sellerId, State state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.sellerId = sellerId;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialPrice=" + initialPrice +
                ", finalPrice=" + finalPrice +
                ", sellerId='" + sellerId + '\'' +
                ", state=" + state +
                '}';
    }


}
