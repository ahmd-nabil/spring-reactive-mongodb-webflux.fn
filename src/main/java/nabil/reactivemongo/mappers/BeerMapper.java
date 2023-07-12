package nabil.reactivemongo.mappers;

import nabil.reactivemongo.domain.Beer;
import nabil.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDTO beerDTO);

}
