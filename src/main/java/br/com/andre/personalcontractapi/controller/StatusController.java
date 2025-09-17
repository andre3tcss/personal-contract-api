package br.com.andre.personalcontractapi.controller;

import br.com.andre.personalcontractapi.dto.StatusDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")
public class StatusController {

    @GetMapping("/status")
    public StatusDTO getStatus() {
        return new StatusDTO("API is running... v1.0.0");
    }
}