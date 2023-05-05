package com.thejaxonhill.mtg.model;

public enum ColorIdentity {

    R("Red"),
    U("Blue"),
    G("Green"),
    W("White"),
    B("Black");

    private String color;

    public String color() {
        return this.color;
    }

    private ColorIdentity(String color) {
        this.color = color;
    }

}
