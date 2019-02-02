package contracts.card

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Get One Card Should Return Successfully")

    request{
        method 'GET'
        url '/1'
    }

    response {
        status OK()
        body("""
                {
                "id": 1,
                "number": "4409 3350 3050 2136",
                "holderName": "Sam",
                "expMonth": "10",
                "expYear": "2020",
                "code": "333",
                "max": "1500.00",
                "balance": "1000.00"
                }
        """)
        headers {
            contentType('application/json')
        }
    }
}