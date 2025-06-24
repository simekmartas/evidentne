package cz.financniporadce.crm.entity;

public enum WorkflowStage {
    NAVOLANI("Navolání"),
    ANALYZA_POTREB("Analýza potřeb"),
    ZPRACOVANI("Zpracování"),
    PRODEJNI_SCHUZKA("Prodejní schůzka"),
    PODPIS("Podpis"),
    SERVIS("Servis");
    
    private final String displayName;
    
    WorkflowStage(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 