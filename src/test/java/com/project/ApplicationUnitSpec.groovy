package com.project

import spock.lang.Specification

class ApplicationUnitSpec extends Specification {
    def application

    def setup() {
        application = new Application()
    }

    def "First test"() {
        expect:
            application != null
    }
}
