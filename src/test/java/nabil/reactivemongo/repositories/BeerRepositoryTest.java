package nabil.reactivemongo.repositories;

import nabil.reactivemongo.domain.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ahmed Nabil
 */
@DataMongoTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;
    Beer beer;

    @BeforeEach
    void setup() {
        beer = Beer.builder()
                .beerName("beerName")
                .beerStyle("beerStyle")
                .price(BigDecimal.TEN)
                .quantityOnHand(123)
                .upc("upc")
                .build();
    }
    @Test
    void save() {
        Mono<Beer> beerMono = beerRepository
                .save(beer);
        StepVerifier.create(beerMono)
                .expectNextMatches(saved -> saved.getId() != null)
                .verifyComplete();
    }
}