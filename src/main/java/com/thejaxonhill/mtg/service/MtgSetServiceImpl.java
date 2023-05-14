package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.service.MtgCardServiceImpl.MtgCardsResponse;
import com.thejaxonhill.mtg.shared.SerializableHttpClient;
import com.thejaxonhill.mtg.shared.SerializableHttpClientImpl;

import lombok.Builder;

@Builder
public class MtgSetServiceImpl implements MtgSetService {

    private static final String SETS_PATH = "sets";

    private final SerializableHttpClient client;

    public static class MtgSetServiceImplBuilder implements MtgServiceBuilder<MtgSetServiceImplBuilder> {
        public MtgSetServiceImplBuilder client(SerializableHttpClientImpl client) {
            this.client = client;
            return this;
        }
    }

    @Override
    public Optional<MtgSet> get(String id) {
        MtgSetResponse res = client.send(url -> url.addPathSegments(SETS_PATH + "/" + id), MtgSetResponse.class);
        return Optional.ofNullable(res.set());
    }

    @Override
    public List<MtgSet> getAll(MtgSetRequest request) {
        MtgSetsResponse res = client.send(url -> url.addPathSegment(SETS_PATH), request, MtgSetsResponse.class);
        return res == null ? new ArrayList<>() : res.sets();
    }

    @Override
    public List<MtgCard> generateBooster(String code) {
        MtgCardsResponse res = client.send(u -> u.addPathSegments("sets/" + code + "/booster"), MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetResponse(MtgSet set, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetsResponse(List<MtgSet> sets, String status) {
    }

}
