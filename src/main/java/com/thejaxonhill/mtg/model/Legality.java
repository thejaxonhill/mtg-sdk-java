package com.thejaxonhill.mtg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.shared.MutableBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Legality {

    public static class LegalityBuilder implements MutableBuilder<LegalityBuilder> {
    }

    private String format;

    private String legality;

}
