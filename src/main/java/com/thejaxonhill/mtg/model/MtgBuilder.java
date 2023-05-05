package com.thejaxonhill.mtg.model;

import java.util.function.Consumer;

public interface MtgBuilder<B extends MtgBuilder<B>> {

    /**
     * As seen implemented first in AWS SDK for Java
     * 
     * A convenience operator that takes something that will
     * mutate the builder in some way and allows inclusion of it
     * in chaining operations. For example instead of:
     *
     * <pre><code>
     * Builder builder = ClassBeingBuilt.builder();
     * builder = Util.addSomeDetailToTheBuilder(builder);
     * ClassBeingBuilt clz = builder.build();
     * </code></pre>
     * <p>
     * This can be done in a statement:
     *
     * <pre><code>
     * ClassBeingBuilt = ClassBeingBuilt.builder().applyMutation(Util::addSomeDetailToTheBuilder).build();
     * </code></pre>
     *
     * @param mutator the function that mutates the builder
     * @return B the mutated builder instance
     */
    @SuppressWarnings("unchecked")
    default B applyMutation(Consumer<B> mutator) {
        mutator.accept((B) this);
        return (B) this;
    }

}
