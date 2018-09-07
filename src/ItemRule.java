public class ItemRule implements Comparable<ItemRule> {
    private int amount;
    private long price;

    public ItemRule(int amount, long price) {
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public int compareTo(ItemRule otherItemRule) {
        return Integer.compare(this.amount, otherItemRule.amount);
    }
}

