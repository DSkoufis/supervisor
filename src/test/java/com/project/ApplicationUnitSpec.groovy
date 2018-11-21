package com.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import spock.lang.Specification

class ApplicationUnitSpec extends Specification {
    Application application

    def setup() {
        application = new Application()
    }

    def "Class is annotated with Spring Boot's annotation"() {
        expect:
            application.getClass().isAnnotationPresent(SpringBootApplication.class);
    }
}
