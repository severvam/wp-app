package com.code.screening.worldpayapp.user.domain

import com.code.screening.worldpayapp.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class UserRepositorySpec extends IntegrationSpec {

    @Autowired
    UserRepository userRepository

    void 'should select users from database'() {
        when:
            List<User> users = userRepository.findAll()
        then:
            users
            users.size() == 2
    }

}
