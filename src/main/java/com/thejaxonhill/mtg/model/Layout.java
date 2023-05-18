package com.thejaxonhill.mtg.model;

public enum Layout {
    NORMAL("normal"),
    SPLIT("split"),
    DOUBLE_FACED("double-faced"),
    TOKEN("token"),
    PLANE("plane"),
    SCHEME("scheme"),
    PHENOMENON("phenomenon"),
    LEVELER("leveler"),
    VANGUARD("vanguard"),
    AFTERMATH("aftermath");

    private final String value;

    @Override
    public String toString() {
        return value;
    }

    private Layout(String value) {
        this.value = value;
    }
}
