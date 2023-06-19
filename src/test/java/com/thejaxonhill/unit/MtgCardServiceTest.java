package com.thejaxonhill.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.exception.MtgClientException;
import com.thejaxonhill.mtg.model.MtgCardRequest;
import com.thejaxonhill.mtg.service.MtgCardService;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@ExtendWith(MockitoExtension.class)
public class MtgCardServiceTest {

    @Mock
    OkHttpClient client;

    @Spy
    ObjectMapper om;

    @InjectMocks
    MtgCardService cards;

    @Test
    void givenBadResponse_whenGetAll_thenReturnsEmptyList() throws IOException {
        Call call = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException());
        MtgCardRequest request = MtgCardRequest.builder().build();

        Exception ex = assertThrows(MtgClientException.class, () -> cards.all(request));

        assertEquals("Unable to complete call to server.",ex.getMessage());
    }

}
