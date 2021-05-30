package com.example.msscbeerservice.bootstrap;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.mapper.BeerMapper;
import com.example.msscbeerservice.model.BeerDtoList;
import com.example.msscbeerservice.model.BeerStyleEnum;
import com.example.msscbeerservice.repo.BeerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;

    public List<Beer> beers = new ArrayList<>();

    public BeerLoader(BeerRepo beerRepo, BeerMapper beerMapper) {
        this.beerRepo = beerRepo;
        this.beerMapper = beerMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(beerRepo.count() == 0) {
            generateBeer();
            log.info("Saving all test beer data to the database...");
            beerRepo.saveAll(beers);
            log.info("Done!");
        }

        // show all generated beers from the database, so we know the IDs for testing
        BeerDtoList beerDtos = new BeerDtoList(beerRepo
                .findAll()
                .stream()
                .map(beerMapper::beerToBeerDtoMapper)
                .collect(Collectors.toList()));

        log.info("Fetching and printing all test beers from the database...");
        beerDtos.forEach(System.out::println);
        log.info("Done!");
    }

    private void generateBeer() {
        BeerStyleEnum[] beerStyleEnumValues = {BeerStyleEnum.PALE_ALE, BeerStyleEnum.IPA, BeerStyleEnum.ALE};
        String[] names = {"Blue Moon", "Asahi", "Sapporo"};
        String[] price = {"20.12", "23.23", "43.12"};
        log.info("Generating test beer data...");
        for(int i = 0; i < beerStyleEnumValues.length; i++) {
            beers.add(Beer
                    .builder()
                    .id(UUID.randomUUID())
                    .beerName(names[i])
                    .quantityOnHand(200)
                    .price(new BigDecimal(price[i]))
                    .beerStyle(beerStyleEnumValues[i])
                    .upc(getRandomNumberUsingInts())
                    .build());
        }
        log.info("Done!");
    }

    // generate random UPC
    private Integer getRandomNumberUsingInts() {
        Random random = new Random();
        return random.ints(10000000, 20000000)
                .findFirst()
                .getAsInt();
    }
}
