package br.com.andre.personalcontractapi.dto;
import java.math.BigDecimal;

public record ContractResponseDto(
        Long id,
        String clientName,
        String description,
        BigDecimal value
) {
}