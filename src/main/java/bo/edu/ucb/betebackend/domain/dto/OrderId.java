package bo.edu.ucb.betebackend.domain.dto;

public class OrderId {
    private String orderID;

    public OrderId(String orderID) {
        this.orderID = orderID;
    }

    public OrderId() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
