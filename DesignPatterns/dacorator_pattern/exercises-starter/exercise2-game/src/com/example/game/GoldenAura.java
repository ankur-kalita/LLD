package com.example.game;

public class GoldenAura extends CharacterDecorator {

    public GoldenAura(Character wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String getSprite() {
        return "golden_aura.png";
    }

    @Override
    public void move() {
        System.out.println(">> Gliding with Golden Aura! <<");
        wrapper.move();
    }

    @Override
    public void attack() {
        System.out.println(">> Golden Aura Activated! <<");
        wrapper.attack();
    }
    
}
