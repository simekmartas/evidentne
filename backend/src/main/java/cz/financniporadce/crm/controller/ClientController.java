package cz.financniporadce.crm.controller;

import cz.financniporadce.crm.dto.ClientDto;
import cz.financniporadce.crm.entity.User;
import cz.financniporadce.crm.entity.WorkflowStage;
import cz.financniporadce.crm.repository.UserRepository;
import cz.financniporadce.crm.security.UserPrincipal;
import cz.financniporadce.crm.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    
    private final ClientService clientService;
    private final UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User currentUser = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("Uživatel nenalezen"));
        
        List<ClientDto> clients = clientService.getAllClients(currentUser);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        ClientDto client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }
    
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto,
                                                Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        ClientDto createdClient = clientService.createClient(clientDto, userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                                                @Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }
    
    @PutMapping("/{id}/workflow-stage")
    public ResponseEntity<ClientDto> updateWorkflowStage(@PathVariable Long id,
                                                       @RequestParam WorkflowStage stage) {
        ClientDto updatedClient = clientService.updateWorkflowStage(id, stage);
        return ResponseEntity.ok(updatedClient);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VEDOUCI')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ClientDto>> searchClients(@RequestParam String query,
                                                        Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User currentUser = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("Uživatel nenalezen"));
        
        List<ClientDto> clients = clientService.searchClients(query, currentUser);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/workflow/{stage}")
    public ResponseEntity<List<ClientDto>> getClientsByWorkflowStage(@PathVariable WorkflowStage stage) {
        List<ClientDto> clients = clientService.getClientsByWorkflowStage(stage);
        return ResponseEntity.ok(clients);
    }
} 