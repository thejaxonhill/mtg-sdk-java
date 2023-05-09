package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgCardRequest.MtgCardRequestBuilder;

import lombok.Builder;
import okhttp3.OkHttpClient;

public class MtgCardServiceImpl extends AbstractMtgService<MtgCard, MtgCardRequest, MtgCardRequestBuilder>
        implements MtgCardService {

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super("cards", client, om);
    }

    @Override
    public Optional<MtgCard> get(String id) {
        MtgCardResponse res = get(id, MtgCardResponse.class);
        return Optional.ofNullable(res.card());
    }

    @Override
    public List<MtgCard> getAll(MtgCardRequest request) {
        MtgCardsResponse res = get(request, MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardResponse(MtgCard card, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardsResponse(List<MtgCard> cards, String status) {
    }

}
