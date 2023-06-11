package com.thejaxonhill.mtg.service;

import java.util.function.Consumer;

import com.thejaxonhill.mtg.shared.MtgConfig;
import com.thejaxonhill.mtg.shared.MutableBuilder;
import com.thejaxonhill.mtg.shared.SerializableHttpClientImpl;
import com.thejaxonhill.mtg.shared.SerializableHttpClientImpl.SerializableHttpClientImplBuilder;

public interface MtgServiceBuilder<B extends MutableBuilder<B>> {
    
    B client(SerializableHttpClientImpl client);

    default B client(Consumer<SerializableHttpClientImplBuilder> mutator) {
        return client(SerializableHttpClientImpl.builder().applyMutation(mutator).build());
    }

    default B useDefault() {
        return client(c -> c.useDefault(MtgConfig.BASE_URL));
    }

}
