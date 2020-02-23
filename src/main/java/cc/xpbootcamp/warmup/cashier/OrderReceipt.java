package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    private static final String DATE_FORMATTER = "yyyy年M月dd日，EEEE\n\n";
    private static final String SLOGAN = "===== 老王超市，值得信赖 ======\n\n";
    private static final String SEPARATE_LINE = "-----------------------------------\n";
    private static final String TAX_FORMATTER = "税额:   %.2f\n";
    private static final String DISCOUNT_FORMATTER = "折扣：%.2f\n";
    private static final String SUBTOTAL_FORMATTER = "总价:   %.2f\n";

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        return getHeader() +
                getBody() +
                SEPARATE_LINE +
                getFooter();
    }

    private String getHeader() {
        return SLOGAN + order.getCreatedAt().format(DateTimeFormatter.ofPattern(DATE_FORMATTER, Locale.CHINA));
    }

    private String getBody() {
        return order.getGoodsItemList().stream()
                .map(GoodsItem::formatGoodsItem)
                .collect(Collectors.joining());
    }

    private String getFooter() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(TAX_FORMATTER, order.calculateTotalSalesTax()));
        String discountStr = order.hasDiscount() ? String.format(DISCOUNT_FORMATTER, order.calculateDiscount()) : "";
        builder.append(discountStr);
        builder.append(String.format(SUBTOTAL_FORMATTER, order.calculateSubTotalIncludeTaxMinusDiscount()));
        return builder.toString();
    }
}