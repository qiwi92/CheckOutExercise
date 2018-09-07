import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    void SingleScansReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOut();
        checkOut.Scan(ItemIdentifier.Apple);
        checkOut.Scan(ItemIdentifier.Bread);

        assertEquals(50 + 10, checkOut.Total());
    }

    @Test
    void MultipleNotSortedScansReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOut();
        checkOut.Scan(ItemIdentifier.Apple);
        checkOut.Scan(ItemIdentifier.Bread);
        checkOut.Scan(ItemIdentifier.Apple);

        assertEquals(10 + 50 + 10, checkOut.Total());
    }

    @Test
    void ScansWithItemRulesReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOut();
        checkOut.Scan(ItemIdentifier.Apple);
        checkOut.Scan(ItemIdentifier.Bread);
        checkOut.Scan(ItemIdentifier.Apple,3);
        checkOut.Scan(ItemIdentifier.Apple,10);

        assertEquals(10 + 50 + 25 + 80, checkOut.Total());
    }

    @Test
    void ItemsWithoutRuleThrowExceptionWhenScanned() {
        CheckOut checkOut = new CheckOut();

        try{
            checkOut.Scan(ItemIdentifier.Carrot);
        } catch (IllegalArgumentException argumentException){
            return;
        }
    }

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

