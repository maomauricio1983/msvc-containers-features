package com.eazybytes.accounts.controllers;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST API's for Accounts in Eazybank",
        description = "CRUD REST API's in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {


    private IAccountService iAccountService;

    // DESCRIPCION DEL ENDPOINT para swagger
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Acoount inside EazyBank"
    )
    // DESCRIPCION DE LA RESPUESTA QUE ARROJA EL ENDPOINT para swagger
    @ApiResponse(
            responseCode= "201",
            description = "HTTP Status CREATED"

    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    // DESCRIPCION DEL ENDPOINT para swagger
    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch new Customer & Account inside EazyBank"
    )
    // DESCRIPCION DE LA RESPUESTA QUE ARROJA EL ENDPOINT para swagger
    @ApiResponse(
            responseCode= "200",
            description = "HTTP Status OK"

    )
    // busca una cuenta por mobileNumber del cliente
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }


    // DESCRIPCION DEL ENDPOINT para swagger
    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update new Customer & Acoount inside EazyBank"
    )
    // DESCRIPCION DE LA RESPUESTA QUE ARROJA EL ENDPOINT para swagger
    @ApiResponse(
            responseCode= "200 ",
            description = "HTTP Status OK"

    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }




    // DESCRIPCION DEL ENDPOINT para swagger
    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete Customer Or Account inside EazyBank"
    )
    // DESCRIPCION DE LA RESPUESTA QUE ARROJA EL ENDPOINT para swagger
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ",
                    description = "HTTP Status OK"

            ),
            @ApiResponse(
                    responseCode = "500 ",
                    description = "HTTP Status INTERNAL SERVER ERROR"

            ),
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
                                                         String  mobileNumber) {

        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

}
