package com.thejaxonhill.mtg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MtgSetRequest {

    public static class MtgSetRequestBuilder implements MtgBuilder<MtgSetRequestBuilder, MtgSetRequest> {
    }

    private String block;

    private String name;

    private Integer page;

    private Integer pageSize;

}
