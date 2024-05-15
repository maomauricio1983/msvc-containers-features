package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;
import lombok.Data;



@Data
// esta configuracion es para swagger
@Schema(
        name = "Customer",
        description =  "Schema que contenga informacion de Customer and Account"

)
public class CustomerDto {

    @Schema(
            description = "name of the Customer",
            example =  "pepito perez"
    )
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;


    @Schema(
            description = "email of the Customer",
            example =  "pepito@mail.com"
    )
    @NotEmpty(message = "Email Address can't be a null or empty")
    @Email(message = "E,ail Address shoul be a valid value")
    private String email;


    @Schema(
            description = "mobile number of the Customer, must contain 10 characters",
            example =  "3202143770"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    // relacion con AccountsDto
    private AccountsDto accountsDto;
}
