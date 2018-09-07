package CheckOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckOutTests{
    // Hardcoded values in CheckOutMock
    // 1  x APPLE for  10
    // 3  x APPLE for  25
    // 10 x APPLE for  70
    // 1  x BREAD for 100
    // 5  x BREAD for 400
    // 10 x BREAD for 800

    @Test
    void SingleScansReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOutMock();
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);

        assertEquals(10 + 100, checkOut.Total());
    }

    @Test
    void MultipleNotSortedScansReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOutMock();
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE);

        assertEquals(10 + 100 + 10, checkOut.Total());
    }

    @Test
    void ScansWithItemRulesReturnCorrectTotalPrice() {
        CheckOut checkOut = new CheckOutMock();
        checkOut.Scan(ProductMockIds.APPLE);
        checkOut.Scan(ProductMockIds.BREAD);
        checkOut.Scan(ProductMockIds.APPLE,3);
        checkOut.Scan(ProductMockIds.APPLE,10);

        assertEquals(10 + 100 + 25 + 70, checkOut.Total());
    }

    @Test
    void ItemsWithoutRuleThrowExceptionWhenScanned() {
        CheckOut checkOut = new CheckOutMock();

        try{
            checkOut.Scan(ProductMockIds.CARROT);
        } catch (IllegalArgumentException argumentException){
            return;
        }
    }
}
