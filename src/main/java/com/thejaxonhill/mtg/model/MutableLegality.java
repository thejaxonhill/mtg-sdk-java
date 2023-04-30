package com.thejaxonhill.mtg.model;

public interface MutableLegality extends Legality {

    void setFormat(String format);

    void setLegality(String legality);

}
