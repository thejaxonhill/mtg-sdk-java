package com.thejaxonhill.mtg.service;

import java.util.List;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgSet;
import com.thejaxonhill.mtg.model.MtgSetRequest;
import com.thejaxonhill.mtg.model.MtgSetRequest.MtgSetRequestBuilder;
import com.thejaxonhill.mtg.shared.QueryableService;

public interface MtgSets extends QueryableService<MtgSet, MtgSetRequest, MtgSetRequestBuilder> {

    List<MtgCard> generateBooster(String code);

    /**
     * Fetches a list objects based on the given mutator.
     * Any non-null value in the request will be added to
     * the query parameters sent to the server.
     *
     * @param consumer mutator with available request parameters
     * @return List<T> an list of found objects
     */
    @Override
    default List<MtgSet> all(Consumer<MtgSetRequestBuilder> consumer) {
        return all(MtgSetRequest.builder().applyMutation(consumer).build());
    }

}
