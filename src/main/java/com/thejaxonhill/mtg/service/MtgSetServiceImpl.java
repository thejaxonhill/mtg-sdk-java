package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.model.MtgSetRequest.MtgSetRequestBuilder;
import com.thejaxonhill.mtg.service.MtgCardServiceImpl.MtgCardResponse;
import com.thejaxonhill.mtg.service.MtgCardServiceImpl.MtgCardsResponse;

import lombok.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MtgSetServiceImpl extends AbstractMtgService<MtgSet, MtgSetRequest, MtgSetRequestBuilder>
        implements MtgSetService {

    @Builder
    public MtgSetServiceImpl(OkHttpClient client, ObjectMapper om) {
        super("sets", client, om);
    }

    @Override
    public Optional<MtgSet> get(String id) {
        MtgSetResponse res = get(id, MtgSetResponse.class);
        return Optional.ofNullable(res.set());
    }

    @Override
    public List<MtgSet> getAll(MtgSetRequest request) {
        MtgSetsResponse res = get(request, MtgSetsResponse.class);
        return res == null ? new ArrayList<>() : res.sets();
    }

    @Override
    public List<MtgCard> generateBooster(String code) {
        MtgCardsResponse res = deserialize(send(u -> u.addPathSegments(code + "/booster")), MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetResponse(MtgSet set, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetsResponse(List<MtgSet> sets, String status) {
    }

}
