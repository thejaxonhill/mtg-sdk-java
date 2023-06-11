package com.thejaxonhill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.service.MtgCards;
import com.thejaxonhill.mtg.service.MtgCardService;

public class MtgCardServiceTest {

    MtgCards service = MtgCardService.builder()
            .useDefault()
            .build();

    @Test
    void givenColors_whenGetAll_thenAllCardsMatch() {
        MtgCardRequest request = MtgCardRequest.builder().colors("u").build();
        List<MtgCard> cardsList = service.all(request);
        cardsList.forEach(card -> assertTrue(card.getColors().stream()
                .anyMatch(color -> color.equals("U"))));

        List<MtgCard> cardsList2 = service.all(r -> r.colors(e -> e.with("u").and("w")));
        cardsList2.forEach(card -> assertTrue(card.getColors().stream()
                .anyMatch(color -> color.equals("U")) &&
                card.getColors().stream()
                        .anyMatch(color -> color.equals("W"))));
    }

    @Test
    void givenArtists_whenGetAllWithConsumer_thenOk() {
        List<MtgCard> res = service.all(r -> r.artist(e -> e.with("Randy Gallegos").or("Josu Hernaiz")));
        assertFalse(res.isEmpty());
    }

    @Test
    void givenId_whenCardExists_thenNotNull() {
        Optional<MtgCard> res = service.get("3816");
        assertNotNull(res.get());
    }

    @Test
    void givenId_whenCardNotExists_thenNull() {
        Optional<MtgCard> res = service.get("3816131242");
        assertFalse(res.isPresent());
    }

}
