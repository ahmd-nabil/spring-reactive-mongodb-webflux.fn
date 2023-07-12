package nabil.reactivemongo.services;

import nabil.reactivemongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
public interface CustomerService {
    Flux<CustomerDTO> findAll();
    Mono<CustomerDTO> findById(String id);
    Mono<CustomerDTO> save(CustomerDTO customerDTO);
    Mono<CustomerDTO> update(String id, CustomerDTO customerDTO);
    Mono<CustomerDTO> patch(String id, CustomerDTO customerDTO);
    Mono<Void> deleteById(String id);
}
