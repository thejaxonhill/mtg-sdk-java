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
public class ForeignName {
    
    public static class ForeignNameBuilder implements MutableBuilder<ForeignNameBuilder> {
    }

    private String flavor;

    private String imageUrl;

    private String language;

    private Long mulitverseid;

    private String name;

    private String text;

    private String type;

}
