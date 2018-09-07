package ProductRules;

import DataImporter.ProductDataDto;
import DataImporter.ProductDataImporter;
import DataImporter.XMLProductDataImporter;

import java.util.*;

public class ProductRuleManagerExternalData implements ProductRuleManager {
    private Map<String, List<ProductRule>> productMap;

    public ProductRuleManagerExternalData(ProductDataImporter productDataImporter){
        CreateRules(productDataImporter);
    }

    private void CreateRules(ProductDataImporter productDataImporter) {
        this.productMap = new HashMap<>();

        List<ProductDataDto> productDataDtos =  productDataImporter.GetProductDataDtos();

        for(ProductDataDto productDataDto : productDataDtos){
            AddNewRule(productDataDto.Id, new ProductRule( productDataDto.Amount, productDataDto.Price));
        }
        
        //Sorting product rules
        for (String productId : productMap.keySet()) {
            Collections.sort(productMap.get(productId), Collections.reverseOrder());
        }
    }

    public boolean DoesItemHaveRule (String productId){
        return productMap.containsKey(productId);
    }

    public List<ProductRule> GetRulesForItem(String itemIdentifier) {
        if(!productMap.containsKey(itemIdentifier)){
            return null;
        }

        return productMap.get(itemIdentifier);
    }

    private void AddNewRule(String productId, ProductRule productRule) {
        if (productMap.containsKey(productId)) {
            productMap.get(productId).add(productRule);
        } else {
            LinkedList<ProductRule> newItemRuleList = new LinkedList<>();
            newItemRuleList.add(productRule);

            productMap.put(productId, newItemRuleList);
        }
    }
}
