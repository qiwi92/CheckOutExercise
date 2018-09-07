import java.util.*;

public class ItemRuleManager {
    private Map<ItemIdentifier, List<ItemRule>> itemRulesMap;

    public void CreateRules() {
        this.itemRulesMap = new HashMap<>();

        ProductDataImporter productDataImporter = new XMLDataImporter();

        //In a real application this should be loaded from a serialized JSON or XML file
        AddNewRule(ItemIdentifier.Apple, new ItemRule( 1, 10));
        AddNewRule(ItemIdentifier.Apple, new ItemRule( 3, 25));
        AddNewRule(ItemIdentifier.Apple, new ItemRule(10, 80));
        AddNewRule(ItemIdentifier.Apple, new ItemRule(50,300));

        AddNewRule(ItemIdentifier.Bread, new ItemRule( 1,  50));
        AddNewRule(ItemIdentifier.Bread, new ItemRule(10, 200));

        for (ItemIdentifier itemIdentifier : itemRulesMap.keySet()) {
            Collections.sort(itemRulesMap.get(itemIdentifier), Collections.reverseOrder());
        }
    }

    public boolean DoesItemHaveRule (ItemIdentifier itemIdentifier){
        return itemRulesMap.containsKey(itemIdentifier);
    }

    public List<ItemRule> GetRulesForItem(ItemIdentifier itemIdentifier) {
        if(!itemRulesMap.containsKey(itemIdentifier)){
            return null;
        }

        return itemRulesMap.get(itemIdentifier);
    }

    private void AddNewRule(ItemIdentifier itemIdentifier, ItemRule itemRule) {
        if (itemRulesMap.containsKey(itemIdentifier)) {
            itemRulesMap.get(itemIdentifier).add(itemRule);
        } else {
            LinkedList<ItemRule> newItemRuleList = new LinkedList<>();
            newItemRuleList.add(itemRule);

            itemRulesMap.put(itemIdentifier, newItemRuleList);
        }
    }
}
