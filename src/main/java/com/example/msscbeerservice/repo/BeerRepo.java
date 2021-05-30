package com.example.msscbeerservice.repo;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {

    Page<Beer> findAllByBeerNameAndBeerStyle(String name, BeerStyleEnum beerStyleEnum, Pageable pageable);
    Page<Beer> findAllByBeerName(String name, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyleEnum, Pageable pageable);
}
