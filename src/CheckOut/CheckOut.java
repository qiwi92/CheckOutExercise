package CheckOut;

public interface CheckOut{
    void Scan(String productId);
    void Scan(String productId, int amount);
    long Total();
}

