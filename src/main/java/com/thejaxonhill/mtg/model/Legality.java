package com.thejaxonhill.mtg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    public static class LegalityBuilder implements MtgBuilder<LegalityBuilder, Legality> {
    }

    private String format;

    private String legality;

}
