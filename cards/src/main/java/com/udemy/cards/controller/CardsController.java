package com.udemy.cards.controller;

import com.udemy.cards.constants.CardsConstants;
import com.udemy.cards.dto.CardsContactInfoDTO;
import com.udemy.cards.dto.CardsDto;
import com.udemy.cards.dto.ErrorResponseDTO;
import com.udemy.cards.dto.ResponseDTO;
import com.udemy.cards.service.impl.CardsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {
    @Autowired
    private CardsServiceImpl cardsService;

    @Autowired
    private CardsContactInfoDTO cardsContactInfoDto;

    @Operation(
            summary = "CREATE card REST API",
            description = "REST API to create new card inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(String mobileNumber){
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "FETCH card REST API",
            description = "REST API to fetch cards details based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                      @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
                                                      String mobileNumber){
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsService.fetchCard(mobileNumber));
    }

    @Operation(
            summary = "UPDATE card REST API",
            description = "REST API to update a card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update operation failed. Please try again or contact Dev team"
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@Valid @RequestBody CardsDto cardsDto){
        boolean isUptated = cardsService.updateCard(cardsDto);
        if(isUptated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));

        }
    }

    @Operation(
            summary = "DELETE card REST API",
            description = "REST API to delete a card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Delete operation failed. Please try again or contact Dev team"
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(@RequestParam
                                                            @Pattern(regexp = "^$|[0-9]{10}", message = "AccountNumber must be 10 digits")
                                                            String mobileNumber){
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));

        }
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
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDto);
    }

}

