package SolidPrinciples.PaymentProcessor;

public class SQLProductRepo implements DBRepo {
    public void getProductById(int id)  {
        System.out.println("Product being fetched!");
    }
}