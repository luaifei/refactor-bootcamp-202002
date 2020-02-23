package cc.xpbootcamp.warmup.cashier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.1);
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.02);

    private String customerName;
    private String address;
    private List<GoodsItem> goodsItemList;
    private LocalDate createdAt;

    public Order(String customerName, String address, List<GoodsItem> goodsItemList) {
        this(customerName, address, goodsItemList, LocalDate.now());
    }

    BigDecimal getSubTotalExcludeTax() {
        return goodsItemList.stream()
                .map(GoodsItem::getSubTotalExcludeTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal getTotalSalesTax() {
        return TAX_RATE.multiply(getSubTotalExcludeTax());
    }

    BigDecimal getSubTotalIncludeTax() {
        return getSubTotalExcludeTax().add(getTotalSalesTax());
    }

    BigDecimal getDiscount() {
        if (hasDiscount()) {
            return DISCOUNT_RATE.multiply(getSubTotalIncludeTax());
        }

        return BigDecimal.ZERO;
    }

    boolean hasDiscount() {
        return createdAt.getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    BigDecimal getSubTotalIncludeTaxMinusDiscount() {
        return getSubTotalIncludeTax().subtract(getDiscount());
    }
}
