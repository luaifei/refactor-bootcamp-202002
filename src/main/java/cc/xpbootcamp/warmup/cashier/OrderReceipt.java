package cc.xpbootcamp.warmup.cashier;

import java.time.DayOfWeek;
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
    private double discountRate = 0.02;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        return printHeader() +
                printDateAndDayOfWeek() +
                printItems() +
                printSeparateLine() +
                printTotalSalesTax() +
                printDiscount() +
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
        return String.format("税额:   %.2f", totalSalesTax);
    }

    private String printDiscount() {
        LocalDate createdAt = order.getCreatedAt();
        if (createdAt.getDayOfWeek() != DayOfWeek.WEDNESDAY) {
            return "";
        }

        double totalAmount = this.order.getLineItems().stream()
                .mapToDouble(LineItem::getTotalAmountIncludeTax)
                .sum();

        return String.format("折扣：%.2f\n", round2Scale(totalAmount * discountRate));
    }

    private String printTotalAmount() {
        double totalAmount = this.order.getLineItems().stream()
                .mapToDouble(LineItem::getTotalAmountIncludeTax)
                .sum();
        double discount = 0d;
        LocalDate createdAt = order.getCreatedAt();
        if (createdAt.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            discount = totalAmount * discountRate;
        }

        return String.format("总价:   %.2f", round2Scale(totalAmount - discount));
    }

    private double round2Scale(double number) {
        return (double) (Math.round(number * 100)) / 100;
    }
}