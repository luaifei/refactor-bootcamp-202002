package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String customerName;
    private String address;
    private List<GoodsItem> goodsItemList;
    private LocalDate createdAt;

    public Order(String customerName, String address, List<GoodsItem> goodsItemList) {
        this(customerName, address, goodsItemList, LocalDate.now());
    }

    public Order(String customerName, String address, List<GoodsItem> goodsItemList, LocalDate createdAt) {
        this.customerName = customerName;
        this.address = address;
        this.goodsItemList = goodsItemList;
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

    public List<GoodsItem> getGoodsItemList() {
        return goodsItemList;
    }
}
