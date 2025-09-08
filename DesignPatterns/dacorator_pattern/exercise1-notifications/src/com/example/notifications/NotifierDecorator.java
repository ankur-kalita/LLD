package com.example.notifications;

public class NotifierDecorator implements Notifier {
    private Notifier wrapper;

    public NotifierDecorator(Notifier wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void notify(String text) {
        wrapper.notify(text);
    }
    
}
