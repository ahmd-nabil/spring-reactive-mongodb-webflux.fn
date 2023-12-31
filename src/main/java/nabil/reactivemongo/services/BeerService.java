package nabil.reactivemongo.services;

import nabil.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
public interface BeerService {
    Flux<BeerDTO> findAll();
    Mono<BeerDTO> findById(String id);
    Mono<BeerDTO> findFirstByBeerName(String beerName);
    Flux<BeerDTO> findByBeerStyle(String beerStyle);
    Mono<BeerDTO> save(BeerDTO beerDTO);
    Mono<BeerDTO> update(String id, BeerDTO beerDTO);
    Mono<BeerDTO> patch(String id, BeerDTO beerDTO);
    Mono<Void> deleteById(String id);

}
