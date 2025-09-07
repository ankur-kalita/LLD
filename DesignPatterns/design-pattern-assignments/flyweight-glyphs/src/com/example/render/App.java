package com.example.render;
public class App {
    public static void main(String[] args) {
        Renderer r = new Renderer();
        System.out.println("Cost=" + r.render("Hello Flyweight! ".repeat(2000)));

        // TextStyle s1 = TextStyleFactory.get("Inter", 14, true);
        // TextStyle s2 = TextStyleFactory.get("Inter", 14, true);
        // System.out.println("Same instance? " + (s1 == s2));
    }
    // In short Flyweight pattern is an optimization pattern.
    // A flyweight refers to an object that minimizes memory usage by sharing some of the initial object's data with other similar objects.
    // It should only be used when a program must support a huge number of similar objects which barely fit into the available amount of RAM.
    // It splits the state of initial object into two an intrinsic immutable state an extrinsic mutable one.
    // The properties which do not change are called intrinsic attributes and the ones which do change are called extrinsic attributes.
    // Intrinsic arributes are seperated out and stored/cached in the application using factory pattern to access it in the runtime. Those objects
    // are known as flyweights.

}
