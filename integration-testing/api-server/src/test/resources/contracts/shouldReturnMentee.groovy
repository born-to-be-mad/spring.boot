import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return a mentee")
    request {
        method GET()
        url "/mentees/1"
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(id: 1, name: "Dzmitry", grade: 90)
    }
}
