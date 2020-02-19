package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OrderReceiptTest {
    @Test
    void should_print_header_with_slogan() {
        Order order = new Order(null, null, new ArrayList<>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("===== 老王超市，值得信赖 ======"));
    }

    @Test
    void should_print_date_and_day_of_week() {
        LocalDate createdAt = LocalDate.of(2020, 2, 17);

        Order order = new Order(null, null, new ArrayList<>(), createdAt);
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("2020年2月17日，星期一"));
    }

    @Test
    void should_print_line_item() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();
        assertThat(output, containsString("巧克力, 21.50 x 2, 43.00\n"));
        assertThat(output, containsString("小白菜, 10.00 x 1, 10.00"));
    }

    @Test
    void should_print_separate_line() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("-----------------------------------"));
    }

    @Test
    void should_print_total_sales_tax() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();
        assertThat(output, containsString("税额:   5.30"));
    }

    @Test
    void should_print_discount_when_order_create_date_is_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 19);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        assertThat(output, containsString("折扣：1.17"));
    }

    @Test
    void should_not_print_discount_when_order_create_date_is_not_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 17);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        assertThat(output, not(containsString("折扣：1.17")));
    }

    @Test
    void should_print_total_amount_minus_discount_when_order_create_date_is_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 19);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        assertThat(output, containsString("总价:   57.13"));
    }

    @Test
    void should_print_total_amount_without_discount_when_order_create_date_is_not_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 17);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        assertThat(output, containsString("总价:   58.30"));
    }

    @Test
    void should_print_receipt_with_except_formatter_when_order_create_date_is_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 19);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        String separator = System.lineSeparator();
        assertThat(output, equalTo("===== 老王超市，值得信赖 ======" + separator + separator +
                "2020年2月19日，星期三" + separator + separator+
                "巧克力, 21.50 x 2, 43.00" + separator +
                "小白菜, 10.00 x 1, 10.00" + separator +
                "-----------------------------------" + separator +
                "税额:   5.30" + separator +
                "折扣：1.17" + separator +
                "总价:   57.13" + separator));
    }

    @Test
    void should_print_receipt_with_except_formatter_when_order_create_date_is_not_wednesday() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("巧克力", 21.50, 2));
            add(new LineItem("小白菜", 10.00, 1));
        }};
        LocalDate createdAt = LocalDate.of(2020, 2, 17);
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, createdAt));

        String output = receipt.printReceipt();
        String separator = System.lineSeparator();
        assertThat(output, equalTo("===== 老王超市，值得信赖 ======" + separator + separator +
                "2020年2月17日，星期一" + separator + separator +
                "巧克力, 21.50 x 2, 43.00" + separator +
                "小白菜, 10.00 x 1, 10.00" + separator +
                "-----------------------------------" + separator +
                "税额:   5.30" + separator +
                "总价:   58.30" + separator));
    }
}