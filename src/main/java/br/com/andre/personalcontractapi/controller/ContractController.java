package br.com.andre.personalcontractapi.controller;

import jakarta.validation.Valid;
import br.com.andre.personalcontractapi.domain.Contract;
import br.com.andre.personalcontractapi.dto.ContractRequestDto;
import br.com.andre.personalcontractapi.repository.ContractRepository;
import br.com.andre.personalcontractapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;
import br.com.andre.personalcontractapi.dto.ContractResponseDto;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@Valid @RequestBody ContractRequestDto contractDto) {
        Contract newContract = new Contract();
        newContract.setClientName(contractDto.clientName());
        newContract.setDescription(contractDto.description());
        newContract.setValue(contractDto.value());
        Contract savedContract = contractRepository.save(newContract);
        URI location = URI.create("/api/contracts/" + savedContract.getId());
        return ResponseEntity.created(location).body(savedContract);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Optional<Contract> contractOptional = contractRepository.findById(id);
        Contract contract = contractRepository.findById(id) .orElseThrow(() ->
                new ResourceNotFoundException("Contract not found with id: " + id));

        return ResponseEntity.ok(contract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @Valid @RequestBody ContractRequestDto contractDto) {
        Contract existingContract = contractRepository.findById(id) .orElseThrow(() ->
                new ResourceNotFoundException("Contract not found with id: " + id));
        existingContract.setClientName(contractDto.clientName());
        existingContract.setDescription(contractDto.description());
        existingContract.setValue(contractDto.value());

        Contract updatedContract = contractRepository.save(existingContract);

        return ResponseEntity.ok(updatedContract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ContractResponseDto>> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();

        List<ContractResponseDto> contractDtos = contracts.stream()
                .map(contract -> new ContractResponseDto(
                        contract.getId(),
                        contract.getClientName(),
                        contract.getDescription(),
                        contract.getValue()
                ))
                .toList();

        return ResponseEntity.ok(contractDtos);
    }
}