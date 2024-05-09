package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.*;
import lombok.Data;



@Data
public class CustomerDto {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email Address can't be a null or empty")
    @Email(message = "E,ail Address shoul be a valid value")
    private String email;


    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    // relacion con AccountsDto
    private AccountsDto accountsDto;
}
