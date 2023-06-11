package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.client.MtgClient;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MtgSupertypeService extends MtgClient implements MtgSupertypes {

    public List<String> all() {
        MtgSupertypesResponse response = send(url -> url.addPathSegment("supertypes"),
                MtgSupertypesResponse.class);
        return response.supertypes();
    }

    public record MtgSupertypesResponse(List<String> supertypes) {
    }

}
