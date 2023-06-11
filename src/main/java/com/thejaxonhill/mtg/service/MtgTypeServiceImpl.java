package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.shared.SerializableHttpClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MtgTypeServiceImpl implements MtgTypeService {
    
    private final SerializableHttpClient client;

    public List<String> getAll() {
        MtgTypesResponse response = client.send(url -> url.addPathSegment("types"), MtgTypesResponse.class );
        return response.types();
    }

    public record MtgTypesResponse(List<String> types) {}
    
}
