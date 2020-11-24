package com.beans;

public class Utilisateur {
	private int id;
	private String nom;
    private String prenom;

public int getid() {
    return id;
}
public void setid(int id) {
    this.id = id;
}
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws BeanException {
        if (nom.length() > 10) {
            throw new BeanException("Le nom est trop grand ! (10 caractères maximum)");
        }
        else {
            this.nom = nom; 
        }
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}