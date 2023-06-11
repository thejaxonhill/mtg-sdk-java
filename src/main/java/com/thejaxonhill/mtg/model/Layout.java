package com.thejaxonhill.mtg.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Layout {
    @JsonProperty("class")
    CLASS("class"),
    @JsonProperty("normal")
    NORMAL("normal"),
    @JsonProperty("saga")
    SAGA("saga"),
    @JsonProperty("split")
    SPLIT("split"),
    @JsonProperty("double-faced")
    DOUBLE_FACED("double-faced"),
    @JsonProperty("token")
    TOKEN("token"),
    @JsonProperty("plane")
    PLANE("plane"),
    @JsonProperty("scheme")
    SCHEME("scheme"),
    @JsonProperty("phenomenon")
    PHENOMENON("phenomenon"),
    @JsonProperty("leveler")
    LEVELER("leveler"),
    @JsonProperty("vanguard")
    VANGUARD("vanguard"),
    @JsonProperty("aftermath")
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
