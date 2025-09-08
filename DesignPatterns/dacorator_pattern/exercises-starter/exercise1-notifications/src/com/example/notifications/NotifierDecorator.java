package com.example.notifications;

public abstract class NotifierDecorator implements Notifier {
    public Notifier wrapper;

    public void notify(String text) {
        wrapper.notify(text);
    }
    
}
