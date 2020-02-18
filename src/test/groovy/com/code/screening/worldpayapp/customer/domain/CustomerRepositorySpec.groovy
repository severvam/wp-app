package com.code.screening.worldpayapp.customer.domain

import com.code.screening.worldpayapp.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class CustomerRepositorySpec extends IntegrationSpec {

    @Autowired
    CustomerRepository customerRepository

    void 'should select customers from database'() {
        when:
            List<Customer> customers = customerRepository.findAll()
        then:
            customers
            customers.size() == 2
    }

}
