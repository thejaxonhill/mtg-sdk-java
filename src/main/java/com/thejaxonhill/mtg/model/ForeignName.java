package com.thejaxonhill.mtg.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ForeignNameModel.class)
public interface ForeignName {

    String getFlavor();

    String getImageUrl();

    String getLanguage();

    Long getMulitverseid();

    String getName();

    String getText();

    String getType();

}
