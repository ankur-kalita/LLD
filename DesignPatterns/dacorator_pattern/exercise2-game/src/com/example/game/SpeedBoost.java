package com.example.game;

public class SpeedBoost extends CharacterDecorator {
    private int boost;

    public SpeedBoost(Character wrapper, int boost) {
        this.wrapper = wrapper;
        this.boost = boost;
    }

    @Override
    public int getSpeed() {
        return wrapper.getSpeed() + boost;
    }

    @Override
    public void move() {
        System.out.println("Speed boosted! New speed: " + getSpeed() + " with sprite " + getSprite());
    }
    
}
