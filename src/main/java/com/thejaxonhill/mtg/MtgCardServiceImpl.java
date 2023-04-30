package com.thejaxonhill.mtg;

import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;

import lombok.Builder;
import okhttp3.OkHttpClient;

public class MtgCardServiceImpl extends AbstractMtgService<MtgCard, MtgCardRequest> implements MtgCardService {

    @Builder
    public MtgCardServiceImpl(OkHttpClient client, ObjectMapper om) {
        super("cards", client, om);
    }

    @Override
    public List<MtgCard> getAll(Consumer<MtgCardRequest.MtgCardRequestBuilder> consumer) {
        MtgCardRequest.MtgCardRequestBuilder builder = MtgCardRequest.builder();
        consumer.accept(builder);
        return getAll(builder.build());
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
