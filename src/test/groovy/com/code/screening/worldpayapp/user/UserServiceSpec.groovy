package com.code.screening.worldpayapp.user

import com.code.screening.worldpayapp.user.domain.UserRepository
import com.code.screening.worldpayapp.user.dto.User
import org.springframework.core.convert.ConversionService
import spock.lang.Specification
import spock.lang.Subject

class UserServiceSpec extends Specification {

    UserRepository customerRepository = Mock()
    ConversionService conversionService = Mock()

    @Subject
    UserService userService = new UserService(customerRepository, conversionService)

    void 'should return converted user list'() {
        given:
            def user =  new com.code.screening.worldpayapp.user.domain.User()
            def userDto = new User(null, null)
        when:
            List<User> users = userService.allUsers()
        then:
            1 * customerRepository.findAll() >> [user]
            1 * conversionService.convert(user, User) >> userDto
            users
            users.size() == 1
            users[0] == userDto
    }

}
