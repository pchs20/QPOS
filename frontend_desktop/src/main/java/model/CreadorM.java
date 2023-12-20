package model;

/**
 * Classe que representa el creador de l'esdeveniment
 *
 * @author Enric
 */
public class CreadorM {

    /**
     * Atributs de la classe que representen les dades del creador
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

    /**
     * Constructor buit per a la deserialització JSON
     */
    public CreadorM() {
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

    /**
     * Override del mètode toString per obtenir una representació en cadena de
     * l'objecte
     *
     * @return
     */
    @Override
    public String toString() {
        return "CreadorM{"
                + "id=" + id
                + "dni=" + dni
                + ", nom='" + nom + '\''
                + ", cognoms='" + cognoms + '\''
                + ", telefon='" + telefon + '\''
                + '}';
    }

    /**
     * Atribut per emmagatzemar una llista de creadors
     */
    private CreadorM[] creadors;

    /**
     * Constructor que rep una llista de creadors
     *
     * @param creadors
     */
    public CreadorM(CreadorM[] creadors) {
        this.creadors = creadors;
    }

    /**
     * Mètode per obtenir la llista de creadors
     *
     * @return
     */
    public CreadorM[] getCreadors() {
        return creadors;
    }
}
