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

    private static final String DATE_FORMATTER = "yyyy年M月dd日，EEEE";

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
        return "===== 老王超市，值得信赖 ======" + insertLineSeparator(2);
    }

    private String printDateAndDayOfWeek() {
        return order.getCreatedAt()
                .format(DateTimeFormatter.ofPattern(DATE_FORMATTER, Locale.CHINA))
                + insertLineSeparator(2);
    }

    private String printGoodsItemList() {
        return order.getGoodsItemList().stream()
                .map(this::printGoodsItem)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String printGoodsItem(GoodsItem item) {
        String itemFormatter = "%s, %.2f x %s, %.2f";
        return String.format(itemFormatter, item.getDescription(),
                item.getPrice(), item.getQuantity(), item.getTotalAmountExcludeTax());
    }

    private String printSeparateLine() {
        return insertLineSeparator(1) +
                "-----------------------------------" +
                insertLineSeparator(1);
    }

    private String printTotalSalesTax() {
        return String.format("税额:   %.2f", order.getTotalSalesTax()) + insertLineSeparator(1);
    }

    private String printDiscount() {
        return order.hasDiscount() ? String.format("折扣：%.2f", round2Scale(order.getDiscount()))
                + insertLineSeparator(1)
                : "";
    }

    private String printTotalAmount() {
        return String.format("总价:   %.2f", round2Scale(order.getSubTotalIncludeTaxMinusDiscount())) +
                insertLineSeparator(1);
    }

    private double round2Scale(double number) {
        return (double) (Math.round(number * 100)) / 100;
    }

    private String insertLineSeparator(int times) {
        return String.join("", Collections.nCopies(times, System.lineSeparator()));
    }

}