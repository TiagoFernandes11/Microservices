package com.udemy.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {
    @Schema(
            description = "Name of the Customer", example = "Tiago Fernandes"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The lenght of the customer name shoud be between 5 an 30")
    private String name;

    @Schema(
            description = "E-mail of the Customer", example = "tiagofernandesribeiro@yahoo.com.br"
    )
    @NotEmpty(message = "E-mail can not be null or empty")
    @Email(message = "E-mail address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the Customer", example = "1234567890"
    )
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDTO accountsDTO;
}
