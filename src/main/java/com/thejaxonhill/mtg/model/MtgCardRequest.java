package com.thejaxonhill.mtg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MtgCardRequest {

    private String artist;

    private String cmc;

    private String colors;

    private String colorIdentity;

    private String contains;

    private String flavor;

    private String gameFormat;

    private String id;

    private String layout;

    private String language;

    private String legality;

    private String loyalty;

    private String multiverseid;

    private String name;

    private String number;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

    private String power;

    private String rarity;

    private Boolean random;

    private String set;

    private String setName;

    private String superTypes;

    private String subTypes;

    private String text;

    private String toughness;

    private String type;

    private String types;

}
