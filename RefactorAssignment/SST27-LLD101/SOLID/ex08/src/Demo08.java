public class Demo08 {
    public static void main(String[] args) {
        Pedalable v = new Bicycle();
        // v.startEngine(); // crash // now show at error at compile time hehe :)
        v.pedal(5);
    }
}
