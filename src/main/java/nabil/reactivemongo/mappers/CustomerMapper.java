package nabil.reactivemongo.mappers;

import nabil.reactivemongo.domain.Customer;
import nabil.reactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
