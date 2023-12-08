package com.udemy.accounts.controller;

import com.udemy.accounts.constants.AccountConstants;
import com.udemy.accounts.dto.AccountsContactInfoDTO;
import com.udemy.accounts.dto.CustomerDTO;
import com.udemy.accounts.dto.ErrorResponseDTO;
import com.udemy.accounts.dto.ResponseDTO;
import com.udemy.accounts.service.IAccountsService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IAccountsService iAccountsService;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDTO accountsContactInfoDto;

    @Operation(
            summary = "CREATE account REST API",
            description = "REST API to create new Customer & Account inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "FETCH account REST API",
            description = "REST API to fetch new Customer & Account details based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
                                                               String mobileNumber){
        CustomerDTO customerDTO = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK). body(customerDTO);
    }

    @Operation(
            summary = "UPDATE account REST API",
            description = "REST API to update Customer & Account inside EazyBank"
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated = iAccountsService.updateAccount(customerDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));

        }
    }

    @Operation(
            summary = "DELETE account REST API",
            description = "REST API to delete a Customer & Account details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
                                                                String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));

        }
    }

    @Operation(
            summary = "Get Java Version Info",
            description = "Java Version Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Java 17");
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @Retry(name = "getContactInfo", fallbackMethod = "getContactInfoFallback")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDTO> getContactInfo() {
        logger.debug("getContactInfo() method invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

    public ResponseEntity<String> getContactInfoFallback(Throwable throwable){
        logger.debug("getContactInfoFallback() method invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Throwback contact-info message");
    }
}
