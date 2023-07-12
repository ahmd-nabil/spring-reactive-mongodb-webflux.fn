package nabil.reactivemongo.services;

import nabil.reactivemongo.domain.Beer;
import nabil.reactivemongo.mappers.BeerMapper;
import nabil.reactivemongo.model.BeerDTO;
import nabil.reactivemongo.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ahmed Nabil
 */
@SpringBootTest
class BeerServiceTest {
    @Autowired
    BeerService beerService;
    @Autowired
    BeerMapper beerMapper;
    Beer beer;

    @BeforeEach
    void setup() {
        beer = Beer.builder()
                .beerName("testService")
                .beerStyle("beerStyle")
                .price(BigDecimal.TEN)
                .quantityOnHand(123)
                .upc("upc")
                .build();
    }

    @Test
    void save() {
        Mono<BeerDTO> beerMono = beerService
                .save(beerMapper.beerToBeerDto(beer));
        StepVerifier.create(beerMono)
                .expectNextMatches(saved -> saved.getId() != null)
                .verifyComplete();
    }
}