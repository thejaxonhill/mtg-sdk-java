package com.thejaxonhill.mtg.service;

import java.util.List;
import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgCardRequest.MtgCardRequestBuilder;
import com.thejaxonhill.mtg.shared.QueryableService;

public interface MtgCards extends QueryableService<MtgCard, MtgCardRequest, MtgCardRequestBuilder> {

}
