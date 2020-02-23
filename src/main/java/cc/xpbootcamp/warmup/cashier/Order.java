package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private static final double TAX_RATE = 0.1;

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

    double getSubTotalExcludeTax() {
        return goodsItemList.stream()
                .mapToDouble(GoodsItem::getTotalAmountExcludeTax)
                .sum();
    }

    double getTotalSalesTax() {
        return getSubTotalExcludeTax() * TAX_RATE;
    }

    double getSubTotalIncludeTax() {
        return getSubTotalExcludeTax() + getTotalSalesTax();
    }
}
