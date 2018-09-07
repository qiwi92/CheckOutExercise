package CheckOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

import DataImporter.ProductDataDto;
import DataImporter.ProductDataImporter;
import ProductRules.ProductRuleManager;
import ProductRules.ProductRuleManagerExternalData;
import org.junit.jupiter.api.Test;

import java.util.*;


public class SuperMarketCheckOutTests {

    private ProductDataImporter productDataImporterMock = new ProductDataImporter() {
        @Override
        public List<ProductDataDto> GetProductDataDtos() {
            List<ProductDataDto> productDataDtos = new ArrayList<>();
            productDataDtos.add( new ProductDataDto(ProductMockIds.APPLE,ProductMockIds.APPLE,1,10));
            productDataDtos.add( new ProductDataDto(ProductMockIds.APPLE,ProductMockIds.APPLE,3,25));
            productDataDtos.add( new ProductDataDto(ProductMockIds.APPLE,ProductMockIds.APPLE,10,70));
            productDataDtos.add( new ProductDataDto(ProductMockIds.BREAD,ProductMockIds.BREAD,1,100));
            productDataDtos.add( new ProductDataDto(ProductMockIds.BREAD,ProductMockIds.BREAD,5,400));
            productDataDtos.add( new ProductDataDto(ProductMockIds.BREAD,ProductMockIds.BREAD,10,700));

            return productDataDtos;
        }
    };

    private ProductRuleManager productRuleManager = new ProductRuleManagerExternalData(productDataImporterMock);



    @Test
    void SingleScansReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManager);
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);

        assertEquals(10 + 100, checkOut.Total());
    }

    @Test
    void MultipleNotSortedScansReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManager);
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE);

        assertEquals(10 + 100 + 10, checkOut.Total());
    }

    @Test
    void ScansWithItemRulesReturnCorrectTotalPrice() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManager);
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE,3);
        checkOut.Scan(ProductMockIds.APPLE,10);

        assertEquals(10 + 100 + 25 + 70, checkOut.Total());
    }

    @Test
    void ItemsWithoutRuleThrowExceptionWhenScanned() {

        CheckOut checkOut = new SuperMarketCheckOut(productRuleManager);

        try{
            checkOut.Scan(ProductMockIds.CARROT);
        } catch (IllegalArgumentException argumentException){
            return;
        }
    }
}
