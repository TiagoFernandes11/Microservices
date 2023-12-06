package com.udemy.accounts.controller;

import com.udemy.accounts.dto.CustomerDetailsDTO;
import com.udemy.accounts.service.ICustomerServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST APIs for Customer in EazyBank",
        description = "REST APIs in EazyBank to FETCH Customer Details"
)
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    @Autowired
    private ICustomerServices iCustomerServices;

    @Operation(
            summary = "FETCH CustomerDetails REST API",
            description = "REST API to fetch CustomerDetails inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
                                                                   String mobileNumber){
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerServices.fetchCustomerDetails(mobileNumber));
    }
}
