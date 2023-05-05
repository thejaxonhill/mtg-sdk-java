package com.thejaxonhill.mtg.model;

import java.util.function.Consumer;

public interface MtgBuilder<B extends MtgBuilder<B, T>, T> {

    T build();
    
    @SuppressWarnings("unchecked")
    default B applyMutation(Consumer<B> mutator) {
        mutator.accept((B) this);
        return (B) this;
    }
    
}
