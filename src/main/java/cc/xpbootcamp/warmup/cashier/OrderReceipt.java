package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        // print headers
        return printHeader() +
                printDateAndDayOfWeek() +
                printItems() +
                printSeparateLine() +
                printTotalSalesTax() +
                printTotalAmount();
    }

    private String printHeader() {
        return "===== 老王超市，值得信赖 ======\n";
    }

    private String printDateAndDayOfWeek() {
        LocalDate createdAt = order.getCreatedAt();
        String dateStr = createdAt.format(DateTimeFormatter.ofPattern("yyyy年M月dd日"));
        String dayOfWeek = createdAt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA);
        return dateStr + "，" + dayOfWeek;
    }

    private String printItems() {
        return order.getLineItems().stream()
                .map(this::printLineItem)
                .collect(Collectors.joining("\n"));
    }

    private String printLineItem(LineItem item) {
        String itemFormatter = "%s, %.2f x %s, %.2f";
        return String.format(itemFormatter, item.getDescription(),
                item.getPrice(), item.getQuantity(), item.getTotalAmountExcludeTax());
    }

    private String printSeparateLine() {
        return "-----------------------------------";
    }

    private String printTotalSalesTax() {
        double totalSalesTax = this.order.getLineItems().stream()
                .mapToDouble(LineItem::getTax)
                .sum();
        return "Sales Tax\t" + totalSalesTax;
    }

    private String printTotalAmount() {
        double totalAmount = this.order.getLineItems().stream()
                .mapToDouble(LineItem::getTotalAmountIncludeTax)
                .sum();
        return "Total Amount\t" + totalAmount;
    }
}