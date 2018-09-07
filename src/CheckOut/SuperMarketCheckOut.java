package CheckOut;

import DataImporter.ProductDataImporter;
import ProductRules.ProductRule;
import ProductRules.ProductRuleManager;

import java.util.HashMap;
import java.util.Map;

public class SuperMarketCheckOut implements CheckOut{

    private ProductRuleManager productRuleManager;
    private Map<String, Integer> itemMap = new HashMap<>();

    public SuperMarketCheckOut(ProductRuleManager productRuleManager) {
        this.productRuleManager = productRuleManager;
    }

    public void Scan(String productId) {
        ScanItems(productId, 1);
    }

    public void Scan(String productId, int amount) {
        ScanItems(productId, amount);
    }

    private void ScanItems(String itemIdentifier, int amount){

        if(!productRuleManager.DoesItemHaveRule(itemIdentifier)){
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

        for (Map.Entry<String, Integer> value : itemMap.entrySet()) {
            String itemId = value.getKey();
            long itemAmount = value.getValue();

            for (ProductRule rule : productRuleManager.GetRulesForItem(itemId)) {
                while (itemAmount >= rule.getAmount()) {
                    itemAmount -= rule.getAmount();
                    total += rule.getPrice();
                }
            }
        }

        return total;
    }
}

