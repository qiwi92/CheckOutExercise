package CheckOut;

import ProductManager.ProductRule;
import ProductManager.ProductRuleManager;

import java.util.*;

public class CheckOutMock implements CheckOut{
    private ProductRuleManager itemRuleManagerMock = new ProductRuleManager() {
        private Map<String, List<ProductRule>> productMap;

        @Override
        public void CreateRules() {
            this.productMap = new HashMap<>();

            AddNewRule(ProductMockIds.APPLE, new ProductRule( 1, 10));
            AddNewRule(ProductMockIds.APPLE, new ProductRule( 3, 25));
            AddNewRule(ProductMockIds.APPLE, new ProductRule( 10, 70));
            AddNewRule(ProductMockIds.BREAD, new ProductRule( 1, 100));
            AddNewRule(ProductMockIds.BREAD, new ProductRule( 5, 400));
            AddNewRule(ProductMockIds.BREAD, new ProductRule( 10, 800));

            //Sorting product rules
            for (String productId : productMap.keySet()) {
                Collections.sort(productMap.get(productId), Collections.reverseOrder());
            }
        }

        @Override
        public boolean DoesItemHaveRule(String productId) {
            return productMap.containsKey(productId);
        }

        @Override
        public List<ProductRule> GetRulesForItem(String productId) {
            if(!productMap.containsKey(productId)){
                return null;
            }

            return productMap.get(productId);
        }

        private void AddNewRule(String productId, ProductRule productRule) {
            if (productMap.containsKey(productId)) {
                productMap.get(productId).add(productRule);
                System.out.println("ProductId:" + productId + "Amount: " + productRule.getAmount());
            } else {
                LinkedList<ProductRule> newItemRuleList = new LinkedList<>();
                newItemRuleList.add(productRule);

                productMap.put(productId, newItemRuleList);
            }
        }
    };

    private Map<String, Integer> itemMap = new HashMap<>();

    public CheckOutMock() {
        itemRuleManagerMock.CreateRules();
    }

    public void Scan(String productId) {
        ScanItems(productId, 1);
    }

    public void Scan(String productId, int amount) {
        ScanItems(productId, amount);
    }

    private void ScanItems(String itemIdentifier, int amount){
        if(!itemRuleManagerMock.DoesItemHaveRule(itemIdentifier)){
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

            for (ProductRule rule : itemRuleManagerMock.GetRulesForItem(itemId)) {
                while (itemAmount >= rule.getAmount()) {
                    itemAmount -= rule.getAmount();
                    total += rule.getPrice();
                }
            }
        }

        return total;
    }
}
