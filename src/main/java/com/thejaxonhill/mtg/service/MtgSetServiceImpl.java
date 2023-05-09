package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.model.MtgSetRequest.MtgSetRequestBuilder;
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
    public List<MtgCard> generateBooster(String code) {
        HttpUrl url = buildUrl(u -> u.addPathSegments(code + "/booster"));
        List<MtgCard> booster = deserialize(send(url), MtgCardsResponse.class).cards();
        return booster != null ? booster : new ArrayList<>();
    }

    @Override
    protected MtgSet deserialize(String body) {
        return deserialize(body, MtgSetResponse.class).set();
    }

    @Override
    protected List<MtgSet> deserializeAll(String body) {
        return deserialize(body, MtgSetsResponse.class).sets();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgSetResponse(MtgSet set, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgSetsResponse(List<MtgSet> sets, String status) {
    }

}