package nabil.reactivemongo.services;

import nabil.reactivemongo.domain.Beer;
import nabil.reactivemongo.mappers.BeerMapper;
import nabil.reactivemongo.model.BeerDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ahmed Nabil
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Order(1)
    @Test
    void save() {
        Mono<BeerDTO> beerMono = beerService
                .save(beerMapper.beerToBeerDto(beer));
        StepVerifier.create(beerMono)
                .expectNextMatches(saved -> saved.getId() != null)
                .verifyComplete();
    }

    @Order(100)
    @Test
    void findFirstByBeerName() {
        Mono<BeerDTO> foundDto = beerService.findFirstByBeerName(beer.getBeerName());
        StepVerifier
                .create(foundDto)
                .expectNextMatches(dto -> dto.getBeerName().equals(beer.getBeerName()))
                .verifyComplete();
    }

    @Order(100)
    @Test
    void findAllByBeerStyle() throws InterruptedException {
        Flux<BeerDTO> foundDtos = beerService.findByBeerStyle(beer.getBeerStyle());
        List<BeerDTO> foundList = new ArrayList<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        foundDtos.subscribe(
                foundList::add,
                Throwable::printStackTrace,
                () -> atomicBoolean.set(true)
        );
        // need to wait until subscription is done
        await().untilTrue(atomicBoolean);
        foundList.forEach(dto -> {
            assertEquals(dto.getBeerStyle(), beer.getBeerStyle());
        });
    }
}