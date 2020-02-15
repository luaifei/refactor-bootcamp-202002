package cc.xpbootcamp.warmup.cashier;

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
                // prints lineItems
                printItems() +
                // prints the state tax
                printTotalSalesTax() +
                // print total amount
                printTotalAmount();
    }

    private String printHeader() {
        return "======Printing Orders======\n" +
                order.getCustomerName() +
                order.getCustomerAddress();
    }

    private String printItems() {
        StringBuilder items = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            items.append(lineItem.getDescription());
            items.append('\t');
            items.append(lineItem.getPrice());
            items.append('\t');
            items.append(lineItem.getQuantity());
            items.append('\t');
            items.append(lineItem.getTotalAmountExcludeTax());
            items.append('\n');
        }
        return items.toString();
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