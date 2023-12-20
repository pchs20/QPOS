package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa un client
 *
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientM {

    /**
     * Atributs de la classe que representen les dades del client
     */
    private int id;
    private String user;
    private String username;
    private String nom;
    private String cognoms;
    private String email;
    private String dni;
    private String bio;
    private String dataNaixement;
    private String telefon;
    private String imatge;
    private String punts;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public ClientM() {
    }

    // Mètodes getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getPunts() {
        return punts;
    }

    public void setPunts(String punts) {
        this.punts = punts;
    }

    /**
     * Override del mètode toString per obtenir una representació en cadena de
     * l'objecte
     *
     * @return
     */
    @Override
    public String toString() {
        return "ClientM{"
                + "id=" + id
                + "dni=" + dni
                + ", nom='" + nom + '\''
                + ", cognoms='" + cognoms + '\''
                + ", telefon='" + telefon + '\''
                + '}';
    }

    /**
     * Atribut per emmagatzemar una llista de clients
     */
    private ClientM[] clients;

    /**
     * Constructor que rep una llista de clients
     *
     * @param clients
     */
    public ClientM(ClientM[] clients) {
        this.clients = clients;
    }

    /**
     * Mètode per obtenir la llista de clients
     *
     * @return
     */
    public ClientM[] getClients() {
        return clients;
    }
}
