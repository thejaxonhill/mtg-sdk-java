package com.thejaxonhill.mtg.model;

import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgExpression.MtgExpressionBuilder;
import com.thejaxonhill.mtg.shared.MutableBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MtgCardRequest {

    public static class MtgCardRequestBuilder implements MutableBuilder<MtgCardRequestBuilder> {

        public MtgCardRequestBuilder artist(String artist) {
            this.artist = artist;
            return this;
        }

        public MtgCardRequestBuilder artist(MtgExpression expression) {
           return artist(expression.getExpression());
        }
    
        public MtgCardRequestBuilder artist(Consumer<MtgExpressionBuilder> mutator) {
            return artist(MtgExpression.builder().applyMutation(mutator).build());
        }

        public MtgCardRequestBuilder colors(String colors) {
            this.colors = colors;
            return this;
        }

        public MtgCardRequestBuilder colors(MtgExpression expression) {
            return colors(expression.getExpression());
        }
    
        public MtgCardRequestBuilder colors(Consumer<MtgExpressionBuilder> mutator) {
            return colors(MtgExpression.builder().applyMutation(mutator).build());
        }

    }

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
