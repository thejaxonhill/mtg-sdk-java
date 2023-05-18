package com.thejaxonhill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.model.MtgExpression;
import com.thejaxonhill.mtg.service.MtgCardService;
import com.thejaxonhill.mtg.service.MtgCardServiceImpl;

public class MtgCardServiceTest {

    MtgCardService service = MtgCardServiceImpl.builder()
            .useDefault()
            .build();

    @Test
    void givenColors_whenGetAllWithConusmer_thenOk() {
        MtgExpression exp = MtgExpression.builder().with("u").and("w").build();
        MtgExpression exp2 = MtgExpression.builder().with("imageUrl").build();
        MtgCardRequest request = MtgCardRequest.builder().colors(exp).contains(exp2).build();
        List<MtgCard> res1 = service.getAll(request);

        List<MtgCard> res = service.getAll(r -> r.colors(e -> e.with("u").and("w"))
                .contains(e -> e.with("imageUrl")
                .and("multiversId")));
        assertFalse(res.isEmpty());
    }

    @Test
    void givenArtists_whenGetAllWithConsumer_thenOk() {
        List<MtgCard> res = service.getAll(r -> r.artist(e -> e.with("Randy Gallegos").or("Josu Hernaiz")));
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
