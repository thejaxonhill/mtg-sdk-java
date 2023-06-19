package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.client.MtgClient;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgCardRequest.MtgCardRequestBuilder;

import lombok.experimental.SuperBuilder;
import okhttp3.OkHttpClient;

@SuperBuilder
public class MtgCardService extends MtgClient implements MtgCards {

    private static final String CARDS_PATH = "cards";

    public MtgCardService(OkHttpClient client, ObjectMapper om) {
        super(client, om);
    }

    @Override
    public Optional<MtgCard> get(String id) {
        MtgCardResponse res = send(url -> url.addPathSegments(CARDS_PATH + "/" + id), MtgCardResponse.class);
        return Optional.ofNullable(res.card());
    }

    @Override
    public List<MtgCard> all(Consumer<MtgCardRequestBuilder> consumer) {
        return all(MtgCardRequest.builder().applyMutation(consumer).build());
    }

    @Override
    public List<MtgCard> all(MtgCardRequest request) {
        MtgCardsResponse res = send(url -> url.addPathSegment(CARDS_PATH), request, MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardResponse(MtgCard card, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardsResponse(List<MtgCard> cards, String status) {
    }

}
