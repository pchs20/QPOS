package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa un proveidor
 *
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProveidorM {

    /**
     * Atributs de la classe que representen les dades del proveïdor
     */
    private int id;
    private String nom;
    private String descripcio;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public ProveidorM() {

    }

    /**
     * Constructor amb paràmetre
     *
     * @param id
     */
    public ProveidorM(int id) {

    }

    /**
     * Get id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get nom
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set nom
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get descripcio
     *
     * @return
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Set descripcio
     *
     * @param descripcio
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Mètode toString per obtenir una representació en cadena de l'objecte
     *
     * @return
     */
    @Override
    public String toString() {
        return "Proveidor [id=" + id + ", nom=" + nom + ", descripcio=" + descripcio + "]";
    }

    /**
     * Atribut per emmagatzemar una llista de proveidors
     */
    private ProveidorM[] proveidors;

    /**
     * Constructor que rep una llista de proveidors
     *
     * @param proveidors
     */
    public ProveidorM(ProveidorM[] proveidors) {
        this.proveidors = proveidors;
    }

    /**
     * Mètode per obtenir la llista de proveidors
     *
     * @return
     */
    public ProveidorM[] getProveidors() {
        return proveidors;
    }
}
