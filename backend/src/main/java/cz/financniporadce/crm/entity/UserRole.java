package cz.financniporadce.crm.entity;

public enum UserRole {
    VEDOUCI("Vedoucí"),
    PORADCE("Poradce"),
    ASISTENT("Asistent");
    
    private final String displayName;
    
    UserRole(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 