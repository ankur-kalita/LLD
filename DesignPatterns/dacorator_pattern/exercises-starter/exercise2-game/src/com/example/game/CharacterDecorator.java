package com.example.game;

public abstract class CharacterDecorator implements Character {
    Character wrapper;

    @Override
    public void attack() {
        wrapper.attack();
    }

    @Override
    public void move() {
        wrapper.move();
    }

    @Override
    public int getSpeed() {
        return wrapper.getSpeed();
    }

    @Override
    public int getDamage() {
        return wrapper.getDamage();
    }

    @Override
    public String getSprite() {
        return wrapper.getSprite();
    }
    
}
