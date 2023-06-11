package com.thejaxonhill.mtg.client;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejaxonhill.mtg.exception.MtgClientException;

import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@SuperBuilder
public class MtgClient {

    private static final String HOST = "https://api.magicthegathering.io/v1";
    private final OkHttpClient client;
    private final ObjectMapper om;

    public abstract static class MtgClientBuilder<C extends MtgClient, B extends MtgClientBuilder<C, B>> {
        public MtgClientBuilder<C, B> useDefault() {
            this.client = new OkHttpClient.Builder()
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
            this.om = new ObjectMapper();
            return this;
        }
    }

    protected <R> R send(Consumer<HttpUrl.Builder> consumer, Object request, Class<R> clazz) {
        return send(consumer.andThen(builder -> {
            for (Field field : request.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object obj = field.get(request);
                    if (obj != null)
                        builder.addEncodedQueryParameter(field.getName(), obj.toString());
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("{}", e.getMessage());
                    throw new MtgClientException("Unable to attach request parameter.");
                }
            }
        }), clazz);
    }

    protected <R> R send(Consumer<HttpUrl.Builder> consumer, Class<R> clazz) {
        HttpUrl url = acceptUrl(consumer);
        Request req = new Request.Builder().url(url).build();
        Call call = client.newCall(req);
        try {
            Response response = call.execute();
            log.info("{} : Status {}", url.toString(), response.code());
            return deserialize(response.body().string(), clazz);
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            throw new MtgClientException("Unable to complete call to server.");
        }
    }

    private HttpUrl acceptUrl(Consumer<HttpUrl.Builder> consumer) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(HOST).newBuilder();
        consumer.accept(urlBuilder);
        return urlBuilder.build();
    }

    private <R> R deserialize(String body, Class<R> clazz) throws JsonProcessingException {
        return om.readValue(body, clazz);
    }

}