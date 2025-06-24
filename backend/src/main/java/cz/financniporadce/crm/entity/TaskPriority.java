package cz.financniporadce.crm.entity;

public enum TaskPriority {
    LOW("Nízká"),
    MEDIUM("Střední"),
    HIGH("Vysoká"),
    URGENT("Urgentní");
    
    private final String displayName;
    
    TaskPriority(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 