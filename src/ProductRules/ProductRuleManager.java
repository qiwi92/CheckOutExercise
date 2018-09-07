package ProductRules;

import java.util.List;

public interface ProductRuleManager {
    boolean DoesItemHaveRule (String productId);
    List<ProductRule> GetRulesForItem(String itemIdentifier);
}
