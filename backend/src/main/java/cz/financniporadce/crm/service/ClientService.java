package cz.financniporadce.crm.service;

import cz.financniporadce.crm.dto.ClientDto;
import cz.financniporadce.crm.entity.Client;
import cz.financniporadce.crm.entity.User;
import cz.financniporadce.crm.entity.UserRole;
import cz.financniporadce.crm.entity.WorkflowStage;
import cz.financniporadce.crm.repository.ClientRepository;
import cz.financniporadce.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    
    public ClientDto createClient(ClientDto clientDto, Long advisorId) {
        User advisor = userRepository.findById(advisorId)
                .orElseThrow(() -> new RuntimeException("Poradce nenalezen"));
        
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCity(clientDto.getCity());
        client.setPostalCode(clientDto.getPostalCode());
        client.setNotes(clientDto.getNotes());
        client.setWorkflowStage(WorkflowStage.NAVOLANI);
        client.setAdvisor(advisor);
        
        Client savedClient = clientRepository.save(client);
        return mapToDto(savedClient);
    }
    
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klient nenalezen"));
        return mapToDto(client);
    }
    
    public List<ClientDto> getAllClients(User currentUser) {
        List<Client> clients;
        if (currentUser.getRole() == UserRole.VEDOUCI) {
            clients = clientRepository.findAll();
        } else {
            clients = clientRepository.findByAdvisor(currentUser);
        }
        return clients.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    public List<ClientDto> getClientsByAdvisor(Long advisorId) {
        User advisor = userRepository.findById(advisorId)
                .orElseThrow(() -> new RuntimeException("Poradce nenalezen"));
        return clientRepository.findByAdvisor(advisor).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    public List<ClientDto> getClientsByWorkflowStage(WorkflowStage stage) {
        return clientRepository.findByWorkflowStage(stage).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    public List<ClientDto> searchClients(String searchTerm, User currentUser) {
        List<Client> clients;
        if (currentUser.getRole() == UserRole.VEDOUCI) {
            clients = clientRepository.searchClients(searchTerm);
        } else {
            clients = clientRepository.searchClientsByAdvisor(currentUser, searchTerm);
        }
        return clients.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klient nenalezen"));
        
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCity(clientDto.getCity());
        client.setPostalCode(clientDto.getPostalCode());
        client.setNotes(clientDto.getNotes());
        
        Client updatedClient = clientRepository.save(client);
        return mapToDto(updatedClient);
    }
    
    public ClientDto updateWorkflowStage(Long id, WorkflowStage newStage) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Klient nenalezen"));
        
        client.setWorkflowStage(newStage);
        Client updatedClient = clientRepository.save(client);
        return mapToDto(updatedClient);
    }
    
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    
    private ClientDto mapToDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .dateOfBirth(client.getDateOfBirth())
                .email(client.getEmail())
                .phone(client.getPhone())
                .address(client.getAddress())
                .city(client.getCity())
                .postalCode(client.getPostalCode())
                .notes(client.getNotes())
                .workflowStage(client.getWorkflowStage())
                .advisorId(client.getAdvisor().getId())
                .advisorName(client.getAdvisor().getFullName())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .hasNeedsAnalysis(client.getNeedsAnalysis() != null)
                .documentsCount(client.getDocuments().size())
                .meetingsCount(client.getMeetings().size())
                .build();
    }
} 