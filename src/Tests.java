import DataImporter.ProductDataDto;
import DataImporter.ProductDataImporter;
import DataImporter.XMLDataImporter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {


    @Test
    void ImporXML(){
        ProductDataImporter xmlImporter = new XMLDataImporter();
        List<ProductDataDto> productDataDtos = xmlImporter.GetProductDataDtos();

        for(ProductDataDto dto : productDataDtos){
            System.out.println(dto.Id);
            System.out.println(dto.ProductName);
            System.out.println(dto.Amount);
            System.out.println(dto.Price);
        }
    }
}

