import java.util.HashMap;
import java.util.Map;

public class CheckOut {
    private ItemRuleManager itemRuleManager = new ItemRuleManager();
    private Map<ItemIdentifier, Integer> itemMap = new HashMap<>();

    public CheckOut() {
        itemRuleManager.CreateRules();
    }

    public void Scan(ItemIdentifier itemIdentifier) {
        ScanItems(itemIdentifier, 1);
    }

    public void Scan(ItemIdentifier itemIdentifier, int amount) {
        ScanItems(itemIdentifier, amount);
    }

    private void ScanItems(ItemIdentifier itemIdentifier, int amount){
        if(!itemRuleManager.DoesItemHaveRule(itemIdentifier)){
            throw new IllegalArgumentException("Item (" + itemIdentifier + ") does not have an assigned rule");
        }

        if (itemMap.containsKey(itemIdentifier)) {
            itemMap.put(itemIdentifier, itemMap.get(itemIdentifier) + amount);
        } else {
            itemMap.put(itemIdentifier, amount);
        }
    }

    public long Total() {
        long total = 0;

        for (Map.Entry<ItemIdentifier, Integer> value : itemMap.entrySet()) {
            ItemIdentifier itemId = value.getKey();
            long itemAmount = value.getValue();

            for (ItemRule rule : itemRuleManager.GetRulesForItem(itemId)) {
                while (itemAmount >= rule.getAmount()) {
                    itemAmount -= rule.getAmount();
                    total += rule.getPrice();
                }
            }
        }

        return total;
    }


}

