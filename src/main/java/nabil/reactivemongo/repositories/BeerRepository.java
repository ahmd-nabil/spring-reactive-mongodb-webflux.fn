package nabil.reactivemongo.repositories;

import nabil.reactivemongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Ahmed Nabil
 */
public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
