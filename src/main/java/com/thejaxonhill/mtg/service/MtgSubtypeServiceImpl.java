package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.shared.SerializableHttpClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MtgSubtypeServiceImpl implements MtgSubtypeService {
    
    private final SerializableHttpClient client;

    public List<String> getAll() {
        MtgSubtypesResponse response = client.send(url -> url.addPathSegments("subtypes"), MtgSubtypesResponse.class);
        return response.subtypes();
    }

    public record MtgSubtypesResponse(List<String> subtypes){}
    
}
