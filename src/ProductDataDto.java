public class ProductDataDto{
    public String Id;
    public String ProductName;
    public long Amount;
    public long Price;

    public ProductDataDto(String id, String productName, long amount, long price){
        Id = id;
        ProductName = productName;
        Amount = amount;
        Price = price;
    }
}
