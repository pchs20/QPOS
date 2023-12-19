package qpos.com.dam_projecte_qpos.ui.factures;

import java.io.Serializable;

public class LiniaCompra implements Serializable {
    private int id;
    private Producte producte;
    private int quantitat;
    private int compra;



    public LiniaCompra(int id, Producte producte, int quantitat, int compra) {
        this.id = id;
        this.producte = producte;
        this.quantitat = quantitat;
        this.compra = compra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
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
