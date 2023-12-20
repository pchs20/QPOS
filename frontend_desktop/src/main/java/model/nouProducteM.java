package model;

/**
 * Classe per a afegir nous productes
 *
 * @author Enric
 */
public class nouProducteM {

    /**
     * Atributs de la classe que representen les dades del producte
     */
    private int proveidor_id;
    private String nom;
    private String descripcio;
    private double preu;
    private String codiBarres;
    private int estoc;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public nouProducteM() {
    }

    /**
     * Get id del proveidor
     *
     * @return
     */
    public int getProveidor_id() {
        return proveidor_id;
    }

    /**
     * Set id del proveidor
     *
     * @param proveidor_id
     */
    public void setProveidor_id(int proveidor_id) {
        this.proveidor_id = proveidor_id;
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
     * Get preu
     *
     * @return
     */
    public double getPreu() {
        return preu;
    }

    /**
     * Set preu
     *
     * @param preu
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    /**
     * Get codi de barres
     *
     * @return
     */
    public String getCodiBarres() {
        return codiBarres;
    }

    /**
     * Set codi de barres
     *
     * @param codiBarres
     */
    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }

    /**
     * Get estoc
     *
     * @return
     */
    public int getEstoc() {
        return estoc;
    }

    /**
     * Set estoc
     *
     * @param estoc
     */
    public void setEstoc(int estoc) {
        this.estoc = estoc;
    }

}
