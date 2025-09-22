package SolidPrinciples.PaymentProcessor;

public class Main {
    public static void main(String[] args) {
        DBRepo repo = new SQLProductRepo();
        PaymentProcessor processor = new PaymentProcessor(repo);
        processor.pay(123);

        // DBRepo mongoRepo = new MongoProductRepo();
        // PaymentProcessor mongoProcessor = new PaymentProcessor(mongoRepo);
    }
}
