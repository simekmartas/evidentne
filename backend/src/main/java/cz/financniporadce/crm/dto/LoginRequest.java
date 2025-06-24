package cz.financniporadce.crm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    @NotBlank(message = "Uživatelské jméno je povinné")
    private String username;
    
    @NotBlank(message = "Heslo je povinné")
    private String password;
} 