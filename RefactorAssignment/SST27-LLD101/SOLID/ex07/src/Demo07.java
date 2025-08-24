public class Demo07 {
    public static void main(String[] args) {
        Printer m = new BasicPrinter(); 
        m.print("Hello");
        // m.fax("12345"); // blows up // dune ... now shows issue at compile time
        // m.scan("/tmp/out"); // blows up // dune ... now shows issue at compile time
    }
}
