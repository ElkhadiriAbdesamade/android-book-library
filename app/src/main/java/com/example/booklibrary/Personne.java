package com.example.booklibrary;

public class Personne
{
    private int id;
    private String nom;
    private String prenom;
    private String type;
    private String user_name;
    private String email;
    private String password;
    private String tele;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Personne() {
    }

    public Personne(int id,String nom, String prenom, String user_name, String email,String type, String password, String tele)
    {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.user_name = user_name;
        this.email = email;
        this.type=type;
        this.password = password;
        this.tele = tele;
    }

    public Personne(String nom, String prenom, String user_name, String email,String type, String password, String tele)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.user_name = user_name;
        this.email = email;
        this.type=type;
        this.password = password;
        this.tele = tele;
    }


}
