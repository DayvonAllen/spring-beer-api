package com.example.msscbeerservice.mapper;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.exceptions.BreweryException;
import com.example.msscbeerservice.model.BeerDto;
import org.springframework.stereotype.Component;

@Component
public class BeerMapperImpl implements BeerMapper{
    @Override
    public BeerDto beerToBeerDtoMapper(Beer beer) {
        if(beer == null) {
            throw new BreweryException("Error processing data...");
        }
        return BeerDto.builder()
                .id(beer.getId())
                .version(beer.getVersion())
                .createdDate(beer.getCreatedDate())
                .lastModifiedDate(beer.getLastModifiedDate())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .build();
    }

    @Override
    public Beer beerDtoToBeerMapper(BeerDto beerDto) {
        if(beerDto == null) {
            throw new BreweryException("Error processing data...");
        }
        return Beer.builder()
                .id(beerDto.getId())
                .version(beerDto.getVersion())
                .createdDate(beerDto.getCreatedDate())
                .lastModifiedDate(beerDto.getLastModifiedDate())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .price(beerDto.getPrice())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build();
    }
}
