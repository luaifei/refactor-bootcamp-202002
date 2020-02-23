package cc.xpbootcamp.warmup.cashier;

public class GoodsItem {
	private static final String FORMATTER = "%s, %.2f x %s, %.2f\n";

	private String description;
	private double price;
	private int quantity;

	public GoodsItem(String description, double price, int quantity) {
		super();
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double getSubTotalExcludeTax() {
        return price * quantity;
    }

    String formatGoodsItem() {
		return String.format(FORMATTER, description, price, quantity, getSubTotalExcludeTax());
	}
}