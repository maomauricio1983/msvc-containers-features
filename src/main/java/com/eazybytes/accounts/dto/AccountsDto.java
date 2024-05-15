package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description =  "Schema que contenga informacion de Account"

)
public class AccountsDto {

    @Schema(
            description =  "AccountNumber must be 10 digits",  example =  " 1254789632"
    )
    @NotEmpty(message = "AccountNumber can't be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
    private Long accountNumber;


    @Schema(
            description =  "AccountType can not be null or empty", example = "SAVINGS"
    )
    @NotEmpty(message = "AccountType can't be null or empty")
    private String accountType;


    @Schema(
            description =  "BranchAddress must be 10 digits", example =  "123 New York"
    )

    @NotEmpty(message = "BranchAddress can't be null or empty")
    private String branchAddress;
}
