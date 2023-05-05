package com.thejaxonhill.mtg;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgCardRequest.MtgCardRequestBuilder;

import lombok.Builder;
import okhttp3.OkHttpClient;

public class MtgCardServiceImpl extends AbstractMtgService<MtgCard, MtgCardRequest, MtgCardRequestBuilder>
        implements MtgCardService {

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super("cards", client, om);
    }

    @Override
    protected MtgCard deserialize(String body) {
        return deserialize(body, MtgCardResponse.class).card();
    }

    @Override
    protected List<MtgCard> deserializeAll(String body) {
        return deserialize(body, MtgCardsResponse.class).cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardResponse(MtgCard card, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardsResponse(List<MtgCard> cards, String status) {
    }

}
