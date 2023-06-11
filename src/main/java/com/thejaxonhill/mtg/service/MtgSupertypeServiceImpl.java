package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.shared.SerializableHttpClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MtgSupertypeServiceImpl implements MtgSupertypeService {
    
    private final SerializableHttpClient client;

    public List<String> getAll() {
        MtgSupertypesResponse response = client.send(url -> url.addPathSegment("supertypes"), MtgSupertypesResponse.class);
        return response.supertypes();
    }

    public record MtgSupertypesResponse(List<String> supertypes) {}

}
