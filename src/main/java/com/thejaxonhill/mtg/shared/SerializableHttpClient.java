package com.thejaxonhill.mtg.shared;

import java.util.function.Consumer;

import okhttp3.HttpUrl;

public interface SerializableHttpClient {

    <R> R send(Consumer<HttpUrl.Builder> consumer, Object request, Class<R> clazz);
    
    <R> R send(Consumer<HttpUrl.Builder> consumer, Class<R> clazz);
    
}
