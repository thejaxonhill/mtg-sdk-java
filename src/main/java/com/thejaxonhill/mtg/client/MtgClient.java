package com.thejaxonhill.mtg.client;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.exception.MtgClientException;

import lombok.experimental.SuperBuilder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuperBuilder
public class MtgClient {

    private static final String HOST = "https://api.magicthegathering.io/v1";
    private static final ObjectMapper om = new ObjectMapper();
    private final OkHttpClient client;

    public abstract static class MtgClientBuilder<C extends MtgClient, B extends MtgClientBuilder<C, B>> {
        public MtgClientBuilder<C, B> useDefault() {
            this.client = new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
            return this;
        }
    }

    protected <R> R send(Consumer<HttpUrl.Builder> consumer, Object request, Class<R> clazz) {
        return send(consumer.andThen(setParams.apply(request)), clazz);
    }

    private final Function<Object, Consumer<HttpUrl.Builder>> setParams = request -> builder -> {
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                if (obj != null)
                    builder.addEncodedQueryParameter(field.getName(), obj.toString());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new MtgClientException("Unable to attach request parameter.");
            }
        }
    };

    protected <R> R send(Consumer<HttpUrl.Builder> consumer, Class<R> clazz) {
        return deserialize(accept.andThen(buildRequest)
                .andThen(this::call)
                .andThen(readBody)
                .apply(consumer), clazz);
    }

    private final Function<Consumer<HttpUrl.Builder>, HttpUrl> accept = consumer -> {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(HOST).newBuilder();
        consumer.accept(urlBuilder);
        return urlBuilder.build();
    };

    private final Function<HttpUrl, Request> buildRequest = url -> new Request.Builder().url(url).build();

    private Response call(Request req) {
        try {
            return client.newCall(req).execute();
        } catch (IOException e) {
            throw new MtgClientException("Unable to complete call to server.");
        }
    };

    private final Function<Response, String> readBody = res -> {
        try {
            return res.body().toString();
        } catch (Exception e) {
            throw new MtgClientException("Unable to read body from response.");
        }
    };

    private <R> R deserialize(String body, Class<R> clazz) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            throw new MtgClientException("Unable to complete deserialize response from server.");
        }
    }

}