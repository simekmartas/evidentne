package cz.financniporadce.crm.config;

import cz.financniporadce.crm.dto.UserDto;
import cz.financniporadce.crm.entity.UserRole;
import cz.financniporadce.crm.repository.UserRepository;
import cz.financniporadce.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            log.info("Vytváření výchozích uživatelů...");
            
            // Vytvoření vedoucího
            UserDto vedouci = UserDto.builder()
                    .username("vedouci")
                    .firstName("Jan")
                    .lastName("Novák")
                    .email("vedouci@crm.cz")
                    .phone("+420 123 456 789")
                    .role(UserRole.VEDOUCI)
                    .active(true)
                    .build();
            userService.createUser(vedouci, "heslo123");
            log.info("Vytvořen uživatel: vedouci (heslo: heslo123)");
            
            // Vytvoření poradce
            UserDto poradce = UserDto.builder()
                    .username("poradce")
                    .firstName("Petr")
                    .lastName("Svoboda")
                    .email("poradce@crm.cz")
                    .phone("+420 987 654 321")
                    .role(UserRole.PORADCE)
                    .active(true)
                    .build();
            userService.createUser(poradce, "heslo123");
            log.info("Vytvořen uživatel: poradce (heslo: heslo123)");
            
            // Vytvoření asistenta
            UserDto asistent = UserDto.builder()
                    .username("asistent")
                    .firstName("Marie")
                    .lastName("Dvořáková")
                    .email("asistent@crm.cz")
                    .phone("+420 555 666 777")
                    .role(UserRole.ASISTENT)
                    .active(true)
                    .build();
            userService.createUser(asistent, "heslo123");
            log.info("Vytvořen uživatel: asistent (heslo: heslo123)");
            
            log.info("Výchozí uživatelé byli úspěšně vytvořeni.");
        }
    }
} 