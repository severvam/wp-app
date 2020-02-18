package com.code.screening.worldpayapp.customer


import com.code.screening.worldpayapp.customer.domain.CustomerRepository
import com.code.screening.worldpayapp.customer.dto.Customer
import org.springframework.core.convert.ConversionService
import spock.lang.Specification
import spock.lang.Subject

class CustomerServiceSpec extends Specification {

    CustomerRepository customerRepository = Mock()
    ConversionService conversionService = Mock()

    @Subject
    CustomerService currencyService = new CustomerService(customerRepository, conversionService)

    void 'should return converted customer list'() {
        given:
            def customer =  new com.code.screening.worldpayapp.customer.domain.Customer()
            def customerDto = Customer.builder().build()
        when:
            List<Customer> customers = currencyService.allCustomers()
        then:
            1 * customerRepository.findAll() >> [customer]
            1 * conversionService.convert(customer, Customer) >> customerDto
            customers
            customers.size() == 1
            customers[0] == customerDto
    }

}
