package com.thejaxonhill.mtg.model;

import com.thejaxonhill.mtg.shared.MutableBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MtgSetRequest {

    public static class MtgSetRequestBuilder implements MutableBuilder<MtgSetRequestBuilder> {
    }

    private String block;

    private String name;

    private Integer page;

    private Integer pageSize;

}
