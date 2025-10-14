package br.com.andre.personalcontractapi.controller;

import br.com.andre.personalcontractapi.domain.Contract;
import br.com.andre.personalcontractapi.dto.ContractRequestDto;
import br.com.andre.personalcontractapi.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody ContractRequestDto contractDto) {
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
        return contractOptional
                .map(contract -> ResponseEntity.ok(contract))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody ContractRequestDto contractDto) {
        return contractRepository.findById(id)
                .map(existingContract -> {
                    existingContract.setClientName(contractDto.clientName());
                    existingContract.setDescription(contractDto.description());
                    existingContract.setValue(contractDto.value());
                    Contract updatedContract = contractRepository.save(existingContract);
                    return ResponseEntity.ok(updatedContract);
                })
                .orElse(ResponseEntity.notFound().build());
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
}