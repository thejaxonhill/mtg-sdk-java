package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.shared.SerializableHttpClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MtgFormatServiceImpl implements MtgFormatService {
    
    private final SerializableHttpClient client;

    public List<String> getAll() {
        MtgFormatsResponse response = client.send(url -> url.addPathSegments("formats"), MtgFormatsResponse.class);
        return response.formats();
    }

    public record MtgFormatsResponse(List<String> formats){}

}
