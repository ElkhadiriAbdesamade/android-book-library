package com.example.booklibrary;

import android.media.Image;

public class Livre
{
    private int id;
    private String titre;
    private String editeur;
    private String genre;
    private String description ;
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public Livre(int id, String titre, String editeur, String genre, String description, byte[] image) {
        this.id = id;
        this.titre = titre;
        this.editeur = editeur;
        this.genre=genre;
        this.description=description;
        this.image = image;
    }
    public Livre( String titre, String editeur, String genre, String description, byte[] image) {
        this.titre = titre;
        this.editeur = editeur;
        this.genre=genre;
        this.description=description;
        this.image = image;


    }

}
