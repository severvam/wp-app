package com.code.screening.worldpayapp.customer.dto


import spock.lang.Specification
import spock.lang.Unroll

class CustomerToDtoConverterSpec extends Specification {

    CustomerToDtoConverter converter = new CustomerToDtoConverter()

    @Unroll
    void 'should convert from domain customer to dto'() {
        when:
            Customer customerDto = converter.convert(createCustomer(id, name, surname))
        then:
            customerDto.id == id
            customerDto.name == name
            customerDto.surname == surname
        where:
            id      || name  || surname
            1l      || 'Aaa' || 'Bbb'
            null    || 'Aaa' || 'Bbb'
            1l      || null  || 'Bbb'
            1l      || 'Aaa' || null
    }

    com.code.screening.worldpayapp.customer.domain.Customer createCustomer(Long id, String name, String surname) {
        new com.code.screening.worldpayapp.customer.domain.Customer(id: id, name: name, surname: surname)
    }

}
