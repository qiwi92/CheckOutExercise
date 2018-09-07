package DataImporter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductDataImporterTests {
    @Test
    public void XMLImporterReturnsCorrectFormat(){
        ProductDataImporter xmlImporter = new XMLProductDataImporter();

        for(ProductDataDto productDataDto : xmlImporter.GetProductDataDtos()){
            Assertions.assertTrue(!productDataDto.Id.isEmpty());
            Assertions.assertTrue(!productDataDto.ProductName.isEmpty());
            Assertions.assertTrue(productDataDto.Amount > 0);
            Assertions.assertTrue(productDataDto.Price > 0);
        }
    }
}
