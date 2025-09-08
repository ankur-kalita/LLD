package com.example.notifications;

public class WhatsAppDecorator implements Notifier {
    private Notifier wrapper;
    private final String phoneNumber;

    public WhatsAppDecorator(Notifier wrapper, String phoneNumber) {
        this.wrapper = wrapper;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String text) {
        wrapper.notify(text);
        System.out.println("[WHATSAPP -> " + phoneNumber + "]: " + text);
    }
    
}
