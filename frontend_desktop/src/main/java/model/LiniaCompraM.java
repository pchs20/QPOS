package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa una linia de compra
 *
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiniaCompraM {

    /**
     * Atributs de la classe que representen les dades d'una linia de compra
     */
    private int id;
    private ProducteM producte;
    private int quantitat;
    private int compra;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public LiniaCompraM() {
    }

    // Mètodes getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProducteM getProducte() {
        return producte;
    }

    public void setProducte(ProducteM producte) {
        this.producte = producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }
}
