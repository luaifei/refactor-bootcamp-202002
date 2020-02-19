package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String cName;
    private String addr;
    private List<LineItem> lineItemList;
    private LocalDate createdAt;

    public Order(String cName, String addr, List<LineItem> lineItemList) {
        this(cName, addr, lineItemList, LocalDate.now());
    }

    public Order(String cName, String addr, List<LineItem> lineItemList, LocalDate createdAt) {
        this.cName = cName;
        this.addr = addr;
        this.lineItemList = lineItemList;
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }
}
