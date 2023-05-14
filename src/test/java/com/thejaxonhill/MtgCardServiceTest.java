package com.thejaxonhill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.service.MtgCardService;
import com.thejaxonhill.mtg.service.MtgCardServiceImpl;

public class MtgCardServiceTest {

    MtgCardService service = MtgCardServiceImpl.builder()
            .useDefault()
            .build();

    @Test
    void testGetAll_withConsumer() {
        List<MtgCard> res = service.getAll(r -> r.colors(e -> e.with("u").and("w")));
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
