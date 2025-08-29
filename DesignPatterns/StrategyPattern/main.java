package DesignPatterns.StrategyPattern;

public class main {
    public static void main(String[] args) {
        DBConnection db1 = DBConnection.DBConnection;
        db1.setName("MySQL");
        System.out.println(db1.getName());
        DBConnection db2 = DBConnection.DBConnection;
        db2.setName("MariaDB");
        System.out.println(db1.getName());
    }
}
