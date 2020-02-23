package cc.xpbootcamp.warmup.cashier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class GoodsItem {
	private static final String FORMATTER = "%s, %.2f x %s, %.2f\n";

	private String description;
	private BigDecimal price;
	private int quantity;

    BigDecimal calculateSubTotalExcludeTax() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    String formatGoodsItem() {
		return String.format(FORMATTER, description, price, quantity, calculateSubTotalExcludeTax());
	}
}