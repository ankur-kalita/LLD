public class OrderController {
    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    void create(String id){
        repo.save(id);
        System.out.println("Created order: " + id);
    }
}