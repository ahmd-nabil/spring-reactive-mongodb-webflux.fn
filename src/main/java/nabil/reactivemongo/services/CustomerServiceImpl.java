package nabil.reactivemongo.services;

import lombok.RequiredArgsConstructor;
import nabil.reactivemongo.mappers.CustomerMapper;
import nabil.reactivemongo.model.CustomerDTO;
import nabil.reactivemongo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Flux<CustomerDTO> findAll() {
        return customerRepository
                .findAll()
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> findById(String id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> save(CustomerDTO customerDTO){
        return customerRepository
                .save(customerMapper.customerDtoToCustomer(customerDTO))
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> update(String id, CustomerDTO customerDTO) {
        return customerRepository
                .findById(id)
                .map(foundCustomer -> {
                    foundCustomer.setFirstName(customerDTO.getFirstName());
                    foundCustomer.setLastName(customerDTO.getLastName());
                    return foundCustomer;
                })
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<CustomerDTO> patch(String id, CustomerDTO customerDTO) {
        return customerRepository
                .findById(id)
                .map(foundCustomer -> {
                    if(StringUtils.hasText(customerDTO.getFirstName())) foundCustomer.setFirstName(customerDTO.getFirstName());
                    if(StringUtils.hasText(customerDTO.getLastName())) foundCustomer.setLastName(customerDTO.getLastName());
                    return foundCustomer;
                })
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return customerRepository.deleteById(id);
    }
}
