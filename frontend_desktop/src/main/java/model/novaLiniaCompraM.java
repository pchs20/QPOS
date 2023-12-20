package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe per a afegir noves linies de compra
 *
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class novaLiniaCompraM {

    /**
     * Atributs de la classe que representen les dades de la nova linia de
     * compra
     */
    private int producte;
    private int quantitat;

    /**
     * Constructor amb paràmetres
     *
     * @param producte
     * @param quantitat
     */
    public novaLiniaCompraM(int producte, int quantitat) {
        this.producte = producte;
        this.quantitat = quantitat;
    }

    /**
     * Constructor buit per a la deserialització JSON
     */
    public novaLiniaCompraM() {

    }

    // Mètodes getters i setters
    public int getProducte() {
        return producte;
    }

    public void setProducte(int producte) {
        this.producte = producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

}
