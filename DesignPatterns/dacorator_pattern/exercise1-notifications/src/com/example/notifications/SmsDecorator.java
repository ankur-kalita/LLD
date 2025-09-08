package com.example.notifications;

public class SmsDecorator implements Notifier {
    private Notifier wrapper;
    private final String phoneNumber;

    public SmsDecorator(Notifier wrapper, String phoneNumber) {
        this.wrapper = wrapper;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String text) {
        wrapper.notify(text);
        System.out.println("[SMS -> " + phoneNumber + "]: " + text);
    }
    
}
