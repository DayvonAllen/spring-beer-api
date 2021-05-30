package com.example.msscbeerservice.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerDtoList extends PageImpl<BeerDto> {

    private List<BeerDto> beer;

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public BeerDtoList(
//           @JsonProperty("content") List<BeerDto> content,
//           @JsonProperty("number") int number,
//           @JsonProperty("size") int size,
//           @JsonProperty("totalElement") Long totalElements,
//           @JsonProperty("pageable") JsonNode pageable,
//           @JsonProperty("last") boolean last,
//           @JsonProperty("totalPages") int totalPages,
//           @JsonProperty("sort") JsonNode sort,
//           @JsonProperty("first") boolean first,
//           @JsonProperty("numberOfElements") int numberOfElements
//    ) {
//        super(content, PageRequest.of(number, size), totalElements);
//    }

    public BeerDtoList(
            List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerDtoList(List<BeerDto> content) {
        super(content);
    }
}
