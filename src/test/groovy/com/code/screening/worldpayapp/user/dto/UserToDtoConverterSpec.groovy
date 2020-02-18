package com.code.screening.worldpayapp.user.dto


import spock.lang.Specification
import spock.lang.Unroll

class UserToDtoConverterSpec extends Specification {

    UserToDtoConverter converter = new UserToDtoConverter()

    @Unroll
    void 'should convert from domain user to dto'() {
        when:
            User userDto = converter.convert(createUser(id, displayName))
        then:
            userDto.id == id
            userDto.displayName == displayName
        where:
            id      || displayName
            1l      || 'Aaa'
            null    || 'Aaa'
    }

    com.code.screening.worldpayapp.user.domain.User createUser(Long id, String displayName) {
        new com.code.screening.worldpayapp.user.domain.User(id: id, displayName: displayName)
    }

}
