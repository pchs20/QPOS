package qpos.com.dam_projecte_qpos;

import java.io.Serializable;

public class Client implements Serializable {

    private int id;

    private String username;

    private String password;
    private String password2;
    private String nom;
    private String cognoms;
    private String dni;
    private String bio;
    private String anyNaixement;
    private String email;
    private String telefon;
    private String foto;
    private int punts;

    public Client() {

    }

    public Client(String username, String nom, String cognoms, String email, String password, String password2, String dni, String bio, String anyNaixement, String telefon) {

        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.bio = bio;
        this.anyNaixement = anyNaixement;
        this.email = email;
        this.telefon = telefon;
        //this.foto = foto;
    }
    public Client(int id,String username, String nom, String cognoms, String email, String password, String password2, String dni, String bio, String anyNaixement,int punts, String telefon) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.bio = bio;
        this.anyNaixement = anyNaixement;
        this.email = email;
        this.punts = punts;
        this.telefon = telefon;
        //this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAnyNaixement() {
        return anyNaixement;
    }

    public void setAnyNaixement(String anyNaixement) {
        this.anyNaixement = anyNaixement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }
}
