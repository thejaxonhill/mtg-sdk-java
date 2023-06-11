package com.thejaxonhill.mtg.shared;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Builder
public class SerializableHttpClientImpl implements SerializableHttpClient {

    private final String host;
    private final OkHttpClient client;
    private final ObjectMapper om;

    public static class SerializableHttpClientImplBuilder implements MutableBuilder<SerializableHttpClientImplBuilder> {

        public SerializableHttpClientImplBuilder useDefault(String host) {
            this.host = host;
            return useDefault();
        }

        public SerializableHttpClientImplBuilder useDefault() {
            this.client = new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
            this.om = new ObjectMapper();
            return this;
        }

    }

    public <R> R send(Consumer<HttpUrl.Builder> consumer, Object request, Class<R> clazz) {
        HttpUrl url = setParams(acceptUrl(consumer), request).build();
        return send(url, clazz);
    }

    private HttpUrl.Builder setParams(HttpUrl.Builder builder, Object request) {
        for (Field field : request.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                if (obj != null)
                    builder.addEncodedQueryParameter(field.getName(), obj.toString());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("{}", e.getMessage());
                throw new SerializableHttpClientException("Unable to attach request parameter.");
            }
        }
        return builder;
    }

    public <R> R send(Consumer<HttpUrl.Builder> consumer, Class<R> clazz) {
        HttpUrl url = acceptUrl(consumer).build();
        return send(url, clazz);
    }

    public <R> R send(HttpUrl url, Class<R> clazz) {
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            Response response = call.execute();
            log.info("{} : Status {}", url.toString(), response.code());
            return deserialize(response.body().string(), clazz);
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new SerializableHttpClientException("Unable to complete call to server.");
        }
    }

    private HttpUrl.Builder acceptUrl(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl.Builder urlBuilder = host != null
                ? HttpUrl.parse(host).newBuilder()
                : new HttpUrl.Builder();
        consumer.accept(urlBuilder);
        return urlBuilder;
    }

    private <R> R deserialize(String body, Class<R> clazz) {
        try {
            return om.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("{}", e.getMessage());
            throw new SerializableHttpClientException("Unable to deserialize response.");
        }
    }

}