package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.client.MtgClient;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.model.MtgSetRequest.MtgSetRequestBuilder;
import com.thejaxonhill.mtg.service.MtgCardService.MtgCardsResponse;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MtgSetService extends MtgClient implements MtgSets {

    private static final String SETS_PATH = "sets";

    @Override
    public Optional<MtgSet> get(String id) {
        MtgSetResponse res = send(url -> url.addPathSegments(SETS_PATH + "/" + id), MtgSetResponse.class);
        return Optional.ofNullable(res.set());
    }

    @Override
    public List<MtgSet> all(MtgSetRequest request) {
        MtgSetsResponse res = send(url -> url.addPathSegment(SETS_PATH), request, MtgSetsResponse.class);
        return res == null ? new ArrayList<>() : res.sets();
    }

    @Override
    public List<MtgCard> generateBooster(String code) {
        MtgCardsResponse res = send(u -> u.addPathSegments("sets/" + code + "/booster"), MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetResponse(MtgSet set, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgSetsResponse(List<MtgSet> sets, String status) {
    }

}
