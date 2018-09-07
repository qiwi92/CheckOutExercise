package ProductRules;

public class ProductRule implements Comparable<ProductRule> {
    private long amount;
    private long price;

    public ProductRule(long amount, long price) {
        this.amount = amount;
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public int compareTo(ProductRule otherItemRule) {
        return Long.compare(this.amount, otherItemRule.amount);
    }
}

