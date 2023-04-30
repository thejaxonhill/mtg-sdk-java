package com.thejaxonhill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.MtgCardService;
import com.thejaxonhill.mtg.MtgCardServiceImpl;
import com.thejaxonhill.mtg.model.MtgCard;
import com.thejaxonhill.mtg.model.MtgExpression;
import com.thejaxonhill.mtg.model.MtgExpression.MtgExpressionBuilder;

import okhttp3.OkHttpClient;

public class MtgCardServiceTest {

    MtgCardService service = MtgCardServiceImpl.builder()
            .om(new ObjectMapper())
            .client(new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(5))
                    .connectTimeout(Duration.ofMinutes(5))
                    .readTimeout(Duration.ofMinutes(5))
                    .build())
            .build();

    @Test
    void testGetAll_withConsumer() {
        MtgExpression exp = MtgExpression.builder("u").and("w").build();
        List<MtgCard> res = service.getAll(r -> r.colors("u"));
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
