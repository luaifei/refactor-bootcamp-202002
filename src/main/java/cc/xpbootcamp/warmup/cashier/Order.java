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

    BigDecimal calculateSubTotalExcludeTax() {
        return goodsItemList.stream()
                .map(GoodsItem::calculateSubTotalExcludeTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal calculateTotalSalesTax() {
        return TAX_RATE.multiply(calculateSubTotalExcludeTax());
    }

    BigDecimal calculateSubTotalIncludeTax() {
        return calculateSubTotalExcludeTax().add(calculateTotalSalesTax());
    }

    BigDecimal calculateDiscount() {
        if (hasDiscount()) {
            return DISCOUNT_RATE.multiply(calculateSubTotalIncludeTax());
        }

        return BigDecimal.ZERO;
    }

    boolean hasDiscount() {
        return createdAt.getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    BigDecimal calculateSubTotalIncludeTaxMinusDiscount() {
        return calculateSubTotalIncludeTax().subtract(calculateDiscount());
    }
}
