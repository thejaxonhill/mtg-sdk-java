package com.thejaxonhill.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.model.MtgCard;

import lombok.Builder;

public interface MtgCardService extends MtgService<MtgCard, MtgCardService.MtgCardRequest> {

    List<MtgCard> getAll(Consumer<MtgCardRequest.MtgCardRequestBuilder> consumer);

    @Builder
    record MtgCardRequest (
            String artist,
            String cmc,
            String colors,
            String colorIdentity,
            String contains,
            String flavor,
            String gameFormat,
            String id,
            String layout,
            String language,
            String legality,
            String loyalty,
            String multiverseid,
            String name,
            String number,
            String orderBy,
            Integer page,
            Integer pageSize,
            String power,
            String rarity,
            boolean random,
            String set,
            String setName,
            String superTypes,
            String subTypes,
            String text,
            String toughness,
            String type,
            String types) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgCardResponse(MtgCard card, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgCardsResponse(List<MtgCard> cards, String status) {
    }

}
