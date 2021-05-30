package com.example.msscbeerservice.mapper;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.model.BeerDto;

public interface BeerMapper {

    BeerDto beerToBeerDtoMapper(Beer beer);
    Beer beerDtoToBeerMapper(BeerDto beerDto);

}
