package com.example.msscbeerservice.controller;

import com.example.msscbeerservice.exceptions.BreweryException;
import com.example.msscbeerservice.model.BeerDto;
import com.example.msscbeerservice.model.BeerDtoList;
import com.example.msscbeerservice.model.BeerStyleEnum;
import com.example.msscbeerservice.services.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beer")
@Slf4j
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER= 0;
    private static final Integer DEFAULT_PAGE_SIZE= 25;
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BeerDtoList listBeers(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "beerName") String beerName,
                                 @RequestParam(value = "beerStyle") BeerStyleEnum beerStyle
    ) {
      if (pageNumber == null || pageNumber < 0) {
          pageNumber = DEFAULT_PAGE_NUMBER;
      }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerDtoList beerDtos = beerService.listBeer(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return beerDtos;
    }

    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable UUID beerId) {
        log.info("In the controller method 'getBeerById'");
        return beerService.getBeerById(beerId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@Valid @RequestBody BeerDto beerDto, Errors errors) {
        log.info("In the controller method 'createBeer'");
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return beerService.createBeer(beerDto);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto, Errors errors) {
        log.info("In the controller method 'updateBeer'");
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return beerService.updateBeerById(beerId, beerDto);
    }
}
