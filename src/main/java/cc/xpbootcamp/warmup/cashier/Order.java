package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String customerName;
    private String address;
    private List<LineItem> lineItemList;
    private LocalDate createdAt;

    public Order(String customerName, String address, List<LineItem> lineItemList) {
        this(customerName, address, lineItemList, LocalDate.now());
    }

    public Order(String customerName, String address, List<LineItem> lineItemList, LocalDate createdAt) {
        this.customerName = customerName;
        this.address = address;
        this.lineItemList = lineItemList;
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }
}
