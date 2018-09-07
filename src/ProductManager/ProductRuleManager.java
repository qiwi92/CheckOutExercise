package ProductManager;

import java.util.List;

public interface ProductRuleManager {
    void CreateRules();
    boolean DoesItemHaveRule (String productId);
    List<ProductRule> GetRulesForItem(String itemIdentifier);
}
