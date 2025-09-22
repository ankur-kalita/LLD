public class Demo05 {
    static int areaAfterResize(Rectangle r){
        r.setWidth(5); r.setHeight(4); return r.area();
    }

    static int getArea(Shape s) {
        return s.area();
    }
    public static void main(String[] args) {
        System.out.println(areaAfterResize(new Rectangle(3, 2))); // 20
        System.out.println(getArea(new Rectangle(3, 2))); 
        System.out.println(getArea(new Square(4)));    // 16 (!) violates expectation
    }
}
