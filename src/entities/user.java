package entities;

import java.sql.Date;

public class user {
    private int Idu;
    private String Cin;
    private String Nom;
    private String Prenom;
    private int Role;
        
    private String Tel;
      private String Email;
      private String Password;
      private String Image;
      private Date dateNaissance;


    public user(int Idu, String Cin, String Nom, String Prenom, String Tel, String Email, String Password, String Image, Date dateNaissance) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.dateNaissance = dateNaissance;
    }

    public user(int Idu) {
        this.Idu = Idu;
    }

    public user(int Idu, String Cin, String Nom, String Prenom, String Tel, String Email, String Image, Date dateNaissance) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Image = Image;
        this.dateNaissance = dateNaissance;
    }

    public user(int Idu, String Cin, String Nom, String Prenom, int Role, String Tel, String Email, String Password, String Image, Date dateNaissance) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Role = Role;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.dateNaissance = dateNaissance;
    }

    
    
    public user(String Cin, 
            String Nom,
            String Prenom, 
            String Tel,
            String Email,
            String Password,
            String Image,
            Date dateNaissance) {
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
        this.dateNaissance=dateNaissance;
    }

    public user() {
        
    }

    public user(int Idu, String Cin, String Nom) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
    }

    public user(int Idu, String Cin, String Nom, String Prenom) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
    }

    
    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

 

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public void setCin(String Cin) {
        this.Cin = Cin;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getIdu() {
        return Idu;
    }

    public String getCin() {
        return Cin;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getTel() {
        return Tel;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
    
    
    public String getImage() {
        return Image;
    }

    @Override
    public String toString() {
        return "user{" + "Idu=" + Idu + ", Cin=" + Cin + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", Password=" + Password + ", Image=" + Image + ", dateNaissance=" + dateNaissance + '}';
    }
    
    

    
}
