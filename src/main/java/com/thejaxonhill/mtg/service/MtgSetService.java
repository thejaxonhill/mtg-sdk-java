package com.thejaxonhill.mtg.service;

import java.util.List;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.model.MtgSetRequest.MtgSetRequestBuilder;

public interface MtgSetService extends QueryableService<MtgSet, MtgSetRequest, MtgSetRequestBuilder> {

    List<MtgCard> generateBooster(String code);

    @Override
    default List<MtgSet> getAll(Consumer<MtgSetRequestBuilder> consumer) {
        return getAll(MtgSetRequest.builder().applyMutation(consumer).build());
    }
   
}
