package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;

public class GoodsItem {
	private static final String FORMATTER = "%s, %.2f x %s, %.2f\n";

	private String description;
	private BigDecimal price;
	private int quantity;

	public GoodsItem(String description, double price, int quantity) {
		super();
		this.description = description;
		this.price = BigDecimal.valueOf(price);
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    BigDecimal getSubTotalExcludeTax() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    String formatGoodsItem() {
		return String.format(FORMATTER, description, price, quantity, getSubTotalExcludeTax());
	}
}