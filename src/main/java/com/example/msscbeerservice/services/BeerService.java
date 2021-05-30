package com.example.msscbeerservice.services;

import com.example.msscbeerservice.model.BeerDto;
import com.example.msscbeerservice.model.BeerDtoList;
import com.example.msscbeerservice.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDtoList listBeer(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest);
    BeerDto getBeerById(UUID id);
    BeerDto createBeer(BeerDto beerDto);
    BeerDto updateBeerById(UUID id, BeerDto beerDto);

}
