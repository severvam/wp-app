package com.code.screening.worldpayapp

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification;

@SpringBootTest
class WorldpayAppApplicationSpec extends Specification {

	@Autowired
	ApplicationContext context

	void 'should verify context loads'() {
		expect:
			context
	}

}
