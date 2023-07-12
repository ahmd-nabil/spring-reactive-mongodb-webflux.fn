package nabil.reactivemongo.repositories;

import nabil.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ahmed Nabil
 */
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
