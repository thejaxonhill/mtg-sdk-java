package com.thejaxonhill.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;

public interface MtgCardService extends MtgService<MtgCard, MtgCardRequest> {

    List<MtgCard> getAll(Consumer<MtgCardRequest.MtgCardRequestBuilder> consumer);

}
