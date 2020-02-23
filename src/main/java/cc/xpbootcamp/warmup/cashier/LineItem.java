package cc.xpbootcamp.warmup.cashier;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

	public LineItem(String description, double price, int quantity) {
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

    double getTotalAmountExcludeTax() {
        return price * quantity;
    }

    public double getTax() {
		return this.getTotalAmountExcludeTax() * 0.10;
	}

	public double getTotalAmountIncludeTax() {
		return this.getTotalAmountExcludeTax() + this.getTax();
	}
}