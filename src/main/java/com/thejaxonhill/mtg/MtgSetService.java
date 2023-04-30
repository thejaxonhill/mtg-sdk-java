package com.thejaxonhill.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;

import lombok.Builder;

public interface MtgSetService extends MtgService<MtgSet, MtgSetService.MtgSetRequest> {

    List<MtgSet> getAll(Consumer<MtgSetRequest.MtgSetRequestBuilder> consumer);

    List<MtgCard> generateBooster(String code);

    @Builder
    record MtgSetRequest(
            String block,
            String name,
            int page,
            int pageSize) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgSetResponse(MtgSet set, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record MtgSetsResponse(List<MtgSet> sets, String status) {
    }

}
