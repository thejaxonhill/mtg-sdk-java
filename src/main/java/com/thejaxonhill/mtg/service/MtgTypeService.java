package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.client.MtgClient;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MtgTypeService extends MtgClient implements MtgTypes {

    public List<String> all() {
        MtgTypesResponse response = send(url -> url.addPathSegment("types"), MtgTypesResponse.class);
        return response.types();
    }

    public record MtgTypesResponse(List<String> types) {
    }

}
