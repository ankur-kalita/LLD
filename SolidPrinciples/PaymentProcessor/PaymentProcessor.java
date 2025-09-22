package SolidPrinciples.PaymentProcessor;

public class PaymentProcessor {
    private final DBRepo productRepo;
    
    public PaymentProcessor(DBRepo productRepo) {
        this.productRepo = productRepo;
    }
    
    public void pay(int productID) {
        System.out.println();
        productRepo.getProductById(productID);
        System.out.println("Processing payment for this product...");
        System.out.println();
    }
}
