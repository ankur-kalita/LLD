package com.example.game;

public class DamageBoost extends CharacterDecorator {
    private final int boost;

    public DamageBoost(Character wrapper, int boost) {
        this.wrapper = wrapper;
        this.boost = boost;
    }

    @Override
    public int getDamage() {
        return wrapper.getDamage() + boost;
    }

    @Override
    public void attack() {
        System.out.println("Damage boosted! New damage: " + getDamage() + " using sprite " + getSprite());
    }
    
}
