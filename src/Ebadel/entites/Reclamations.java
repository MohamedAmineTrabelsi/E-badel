/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.entites;

/**
 *
 * @author messaoudi
 */



import java.time.LocalDate;



public class Reclamations {

    private int id;
    private String contact;
    private String destinataire;
    private String type;
    private String statut;
    private String description;
    private LocalDate date;

   

    public Reclamations( String contact, String destinataire, String type, String statut, String description, LocalDate date) {
       
        this.contact = contact;
        this.destinataire = destinataire;
        this.type = type;
        this.statut = statut;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", destinataire='" + destinataire + '\'' +
                ", type='" + type + '\'' +
                ", statut='" + statut + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
