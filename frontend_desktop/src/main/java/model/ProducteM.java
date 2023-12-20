package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa un producte
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProducteM {

    /**
     * Atributs de la classe que representen les dades del producte
     */
    private int id;
    private ProveidorM proveidor;
    private String nom;
    private String descripcio;
    private Number preu;
    private String codiBarres;
    private int estoc;
    private String imatge;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public ProducteM() {
    }

    /**
     * Get id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get proveidor
     * @return
     */
    public ProveidorM getProveidor() {
        return proveidor;
    }

    /**
     * Set proveidor
     * @param proveidor
     */
    public void setProveidor(ProveidorM proveidor) {
        this.proveidor = proveidor;
    }

    /**
     * Get nom
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set nom
     * @param value
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Get descripcio
     * @return
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Set descripcio
     * @param value
     */
    public void setDescripcio(String value) {
        this.descripcio = value;
    }

    /**
     * Get preu
     * @return
     */
    public Number getPreu() {
        return preu;
    }

    /**
     * Set preu
     * @param value
     */
    public void setPreu(Number value) {
        this.preu = value;
    }

    /**
     * Get codi de barres
     * @return
     */
    public String getCodiBarres() {
        return codiBarres;
    }

    /**
     * Set codi de barres
     * @param value
     */
    public void setCodiBarres(String value) {
        this.codiBarres = value;
    }

    /**
     * Get estoc
     * @return
     */
    public int getEstoc() {
        return estoc;
    }

    /**
     * Set estoc
     * @param value
     */
    public void setEstoc(int value) {
        this.estoc = value;
    }

    /**
     * Get imatge
     * @return
     */
    public String getImatge() {
        return imatge;
    }

    /**
     * Set imatge
     * @param value
     */
    public void setImatge(String value) {
        this.imatge = value;
    }

    /**
     * Override del mètode toString per obtenir una representació en cadena de l'objecte
     * @return
     */
    @Override
    public String toString() {
        return "Producte amd id " + id
                + ", amb proveidor " + proveidor
                + ", amb nom " + nom
                + ", descripcio " + descripcio
                + ", amb preu " + preu
                + ", codi de barres " + codiBarres
                + ", amb estoc de " + estoc;
    }

    /**
     * Atribut per emmagatzemar una llista de productes
     */
    private ProducteM[] productes;

    /**
     * Constructor que rep una llista de productes
     * @param productes
     */
    public ProducteM(ProducteM[] productes) {
        this.productes = productes;
    }

    /**
     * Mètode per obtenir la llista de productes
     * @return
     */
    public ProducteM[] getProductes() {
        return productes;
    }

}
