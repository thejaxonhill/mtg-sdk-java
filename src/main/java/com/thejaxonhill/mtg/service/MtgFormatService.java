package com.thejaxonhill.mtg.service;

import java.util.List;

import com.thejaxonhill.mtg.client.MtgClient;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MtgFormatService extends MtgClient implements MtgFormats {

    public List<String> all() {
        MtgFormatsResponse response = send(url -> url.addPathSegments("formats"), MtgFormatsResponse.class);
        return response.formats();
    }

    public record MtgFormatsResponse(List<String> formats) {
    }

}
