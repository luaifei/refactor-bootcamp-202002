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

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        return printHeader() +
                printBody() +
                printSeparateLine() +
                printFooter();
    }

    private String printHeader() {
        return SLOGAN + order.getCreatedAt().format(DateTimeFormatter.ofPattern(DATE_FORMATTER, Locale.CHINA));
    }

    private String printBody() {
        return order.getGoodsItemList().stream()
                .map(GoodsItem::formatGoodsItem)
                .collect(Collectors.joining());
    }

    private String printSeparateLine() {
        return "-----------------------------------\n";
    }

    private String printFooter() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("税额:   %.2f\n", order.getTotalSalesTax()));
        String discountStr = order.hasDiscount() ? String.format("折扣：%.2f\n", order.getDiscount()) : "";
        builder.append(discountStr);
        builder.append(String.format("总价:   %.2f\n", order.getSubTotalIncludeTaxMinusDiscount()));
        return builder.toString();
    }
}