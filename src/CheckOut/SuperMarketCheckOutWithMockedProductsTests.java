package CheckOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ProductManager.ProductRule;
import ProductManager.ProductRuleManager;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SuperMarketCheckOutWithMockedProductsTests {

    private ProductRuleManager productRuleManagerMock = new ProductRuleManager() {

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
            } else {
                LinkedList<ProductRule> newItemRuleList = new LinkedList<>();
                newItemRuleList.add(productRule);

                productMap.put(productId, newItemRuleList);
            }
        }
    };

    @Test
    void SingleScansReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManagerMock);
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);

        assertEquals(10 + 100, checkOut.Total());
    }

    @Test
    void MultipleNotSortedScansReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManagerMock);;
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE);

        assertEquals(10 + 100 + 10, checkOut.Total());
    }

    @Test
    void ScansWithItemRulesReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManagerMock);
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE,3);
        checkOut.Scan(ProductMockIds.APPLE,10);

        assertEquals(10 + 100 + 25 + 70, checkOut.Total());
    }

    @Test
    void ItemsWithoutRuleThrowExceptionWhenScanned() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManagerMock);

        try{
            checkOut.Scan(ProductMockIds.CARROT);
        } catch (IllegalArgumentException argumentException){
            return;
        }
    }
}
