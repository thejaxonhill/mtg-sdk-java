package com.thejaxonhill.mtg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = LegalityModel.class)
public interface Legality {

    String getFormat();

    String getLegality();

}
