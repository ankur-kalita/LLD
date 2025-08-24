public class Demo06 {
    public static void main(String[] args) {
        new Aviary().release(new FlyingBird());
        // new Aviary().release(new Penguin()); // runtime error // now its compile time error
    }
}
