package com.udemy.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Cards",
        description = "Schema to hold card information"
)
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    @Schema(
            description = "Card Number of the customer", example = "548732457654"
    )
    private String cardNumber;

    @NotEmpty(message = "CardType can not be a null or empty")
    @Schema(
            description = "Type of the Card", example = "Credit Card"
    )
    private String cardType;

    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;
}
