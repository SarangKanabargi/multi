package com.example.implementation.controller

import com.example.customer.apis.CustomerApi
import com.example.customer.model.CustomerDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CustomerController : CustomerApi {
    override suspend fun getCustomers(customerId: UUID): ResponseEntity<CustomerDto> {
        return super.getCustomers(customerId)
    }

    override suspend fun postCustomers(customerDto: CustomerDto?): ResponseEntity<Unit> {
        return super.postCustomers(customerDto)
    }
}