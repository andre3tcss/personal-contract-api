package br.com.andre.personalcontractapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ContractRequestDto(
        @NotBlank(message = "Client name cannot be blank")
        String clientName,

        String description,

        @NotNull(message = "Value cannot be null")
        @Positive(message = "Value must be positive")
        BigDecimal value
) {
}