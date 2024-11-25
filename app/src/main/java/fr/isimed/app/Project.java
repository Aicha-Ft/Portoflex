package fr.isimed.app;


/**
 * Classe représentant un projet dans le portfolio.
 */



public class Project {
    private String id; // ID du projet
    private String name;
    private String description;
    private String portfolioId;

    // Constructeur vide requis pour Firebase
    public Project() {
    }

    // Constructeur avec paramètres
    public Project(String id, String name, String description, String portfolioId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.portfolioId = portfolioId;
    }

    // Getter et setter pour l'ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters et setters pour les autres champs
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}
