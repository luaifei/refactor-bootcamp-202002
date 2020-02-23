package cc.xpbootcamp.warmup.cashier;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
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

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        return printHeader() +
                printDateAndDayOfWeek() +
                printGoodsItemList() +
                printSeparateLine() +
                printTotalSalesTax() +
                printDiscount() +
                printTotalAmount();
    }

    private String printHeader() {
        return "===== 老王超市，值得信赖 ======\n\n";
    }

    private String printDateAndDayOfWeek() {
        return order.getCreatedAt().format(DateTimeFormatter.ofPattern(DATE_FORMATTER, Locale.CHINA));
    }

    private String printGoodsItemList() {
        return order.getGoodsItemList().stream()
                .map(this::printGoodsItem)
                .collect(Collectors.joining());
    }

    private String printGoodsItem(GoodsItem item) {
        String itemFormatter = "%s, %.2f x %s, %.2f\n";
        return String.format(itemFormatter, item.getDescription(),
                item.getPrice(), item.getQuantity(), item.getTotalAmountExcludeTax());
    }

    private String printSeparateLine() {
        return "-----------------------------------\n";
    }

    private String printTotalSalesTax() {
        return String.format("税额:   %.2f\n", order.getTotalSalesTax());
    }

    private String printDiscount() {
        return order.hasDiscount() ? String.format("折扣：%.2f\n", order.getDiscount()) : "";
    }

    private String printTotalAmount() {
        return String.format("总价:   %.2f\n", order.getSubTotalIncludeTaxMinusDiscount());
    }
}