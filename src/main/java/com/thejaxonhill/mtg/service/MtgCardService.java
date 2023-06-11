package com.thejaxonhill.mtg.service;

import java.util.List;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgCardRequest.MtgCardRequestBuilder;

public interface MtgCardService extends QueryableService<MtgCard, MtgCardRequest, MtgCardRequestBuilder> {
    
    @Override
    default List<MtgCard> getAll(Consumer<MtgCardRequestBuilder> consumer) {
        return getAll(MtgCardRequest.builder().applyMutation(consumer).build());
    }

}
