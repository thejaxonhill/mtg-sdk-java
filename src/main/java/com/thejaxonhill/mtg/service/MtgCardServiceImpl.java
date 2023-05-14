package com.thejaxonhill.mtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.shared.MtgConfig;
import com.thejaxonhill.mtg.shared.SerializableHttpClient;
import com.thejaxonhill.mtg.shared.SerializableHttpClientImpl;

import lombok.Builder;

@Builder
public class MtgCardServiceImpl implements MtgCardService {

    private static final String CARDS_PATH = "cards";
    
    private final SerializableHttpClient client;

    public static class MtgCardServiceImplBuilder {
        public MtgCardServiceImplBuilder useDefault() {
            this.client = SerializableHttpClientImpl.builder()
                    .useDefault(MtgConfig.BASE_URL)
                    .build();
            return this;
        }
    }

    @Override
    public Optional<MtgCard> get(String id) {
        MtgCardResponse res = client.send(url -> url.addPathSegments(CARDS_PATH + "/" + id),
                MtgCardResponse.class);
        return Optional.ofNullable(res.card());
    }

    @Override
    public List<MtgCard> getAll(MtgCardRequest request) {
        MtgCardsResponse res = client.send(url -> url.addPathSegment(CARDS_PATH), request,
                MtgCardsResponse.class);
        return res == null ? new ArrayList<>() : res.cards();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardResponse(MtgCard card, String status) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MtgCardsResponse(List<MtgCard> cards, String status) {
    }

}
