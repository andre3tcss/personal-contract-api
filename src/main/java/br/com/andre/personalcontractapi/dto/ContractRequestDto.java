package br.com.andre.personalcontractapi.dto;

import java.math.BigDecimal;

public record ContractRequestDto(String clientName, String description, BigDecimal value) {
}