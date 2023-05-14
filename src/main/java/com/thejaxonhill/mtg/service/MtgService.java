package com.thejaxonhill.mtg.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.shared.MutableBuilder;
import com.thejaxonhill.mtg.shared.SerializableHttpClient;
import com.thejaxonhill.mtg.shared.SerializableHttpClientImpl;

public interface MtgService<T, REQ, B extends MutableBuilder<B>> {

    /**
     * Method to fetch a specific object with a known id.
     * If the card exists it will be present, or else the 
     * optional will be empty.
     *
     * @param id the Magic the Gathering object id
     * @return Optional<T> an optional singular instance
     */
    Optional<T> get(String id);

    /**
     * Fetches a list objects based on the given request 
     * object. Any non-null value in the request will be added to 
     * the query parameters sent to the Magic the  Gathering API 
     * server.
     *
     * @param request object with available request parameters
     * @return List<T> an list of found objects
     */
    List<T> getAll(REQ request);

    /**
     * Fetches a list objects based on the given mutator. 
     * Any non-null value in the request will be added to 
     * the query parameters sent to the Magic the  Gathering API 
     * server.
     *
     * @param consumer mutator with available request parameters
     * @return List<T> an list of found objects
     */
    List<T> getAll(Consumer<B> consumer);

}
