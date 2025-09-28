package CaseStudies.ATM.mock;

import CaseStudies.ATM.hardware.*;
import java.util.Map;

public class MockHW {
    public static class Disp implements Display { public void show(String t){ System.out.println("[DISPLAY] " + t); } }
    public static class Keys implements Keypad {
        private String pin = "1234", amount = "2000";
        public void setPin(String p){pin=p;} public void setAmount(String a){amount=a;}
        public String readPin(){ return pin; } public String readAmount(){ return amount; }
    }
    public static class Reader implements CardReader {
        public String readPAN(){ System.out.println("[CARD] read"); return "4111111111111111"; }
        public void eject(){ System.out.println("[CARD] eject"); }
    }
    public static class Cash implements CashDispenser {
        public boolean canDispense(int amount){ return amount <= 20000; }
        public void dispense(Map<Integer,Integer> notes){
            System.out.println("[CASH] " + notes);
        }
    }
    public static class Printer implements ReceiptPrinter {
        public void print(String maskedPan, int amount, int bal){
            System.out.println("[RECEIPT] PAN="+maskedPan+" AMT="+amount+" BAL="+bal);
        }
    }
}

