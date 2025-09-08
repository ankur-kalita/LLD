package com.example.notifications;

public class SlackDecorator implements Notifier {
    private Notifier wrapper;
    private final String channel;

    public SlackDecorator(Notifier wrapper, String channel) {
        this.wrapper = wrapper;
        this.channel = channel;
    }

    @Override
    public void notify(String text) {
        wrapper.notify(text);
        System.out.println("[SLACK -> " + channel + "]: " + text);
    }
    
}
