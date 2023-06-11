package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.client.MtgClient;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MtgSubtypeService extends MtgClient implements MtgSubtypes {

    public List<String> all() {
        MtgSubtypesResponse response = send(url -> url.addPathSegments("subtypes"), MtgSubtypesResponse.class);
        return response.subtypes();
    }

    public record MtgSubtypesResponse(List<String> subtypes) {
    }

}
