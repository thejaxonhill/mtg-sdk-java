package com.thejaxonhill.mtg.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.model.MtgBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@RequiredArgsConstructor
abstract public class AbstractMtgService<T, REQ, B extends MtgBuilder<B>> implements MtgService<T, REQ, B> {

    private static final String HOST = "https://api.magicthegathering.io/v1/";

    private final String basePath;

    private final OkHttpClient client;

    private final ObjectMapper om;

    protected <R> R deserialize(String body, Class<R> clazz) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to deserialize response.");
        }
    }

    protected <R> R get(String id, Class<R> clazz) {
        return deserialize(send(b -> b.addPathSegment(id)), clazz);
    }

    protected <R> R get(REQ request, Class<R> clazz) {
        return deserialize(send(b -> setQueryParams(b, request)), clazz);
    }

    protected void setQueryParams(HttpUrl.Builder urlBuilder, REQ request) {
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                if (obj != null)
                    urlBuilder.addEncodedQueryParameter(field.getName(), obj.toString());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("{}", e.getMessage());
                throw new RuntimeException("Unable to build request url.");
            }
        }
    }

    protected String send(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl url = buildUrl(consumer);
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            Response response = call.execute();
            log.info("{} : Status {}", url.toString(), response.code());
            return response.body().string();
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Unable to complete call to server.");
        }
    }

    private HttpUrl buildUrl(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(HOST + basePath).newBuilder();
        consumer.accept(urlBuilder);
        return urlBuilder.build();
    }

}
