package qpos.com.dam_projecte_qpos.ui.factures;

import java.io.Serializable;
import java.util.List;

import qpos.com.dam_projecte_qpos.Client;

public class Compra implements Serializable {
    private int id;
    private String data;
    private Client client;
    private List<LiniaCompra> liniesCompra;
    private double importFinal;
    private String metodePagament;
    private Double dinersEntregats;
    private Double dinersCanvi;
    private Double descompte;

    public Compra() {
    }

    public Compra(int id, String data, Client client, List<LiniaCompra> liniesCompra, double importFinal, String metodePagament, Double dinersEntregats, Double dinersCanvi, Double descompte) {
        this.id = id;
        this.data = data;
        this.client = client;
        this.liniesCompra = liniesCompra;
        this.importFinal = importFinal;
        this.metodePagament = metodePagament;
        this.dinersEntregats = dinersEntregats;
        this.dinersCanvi = dinersCanvi;
        this.descompte = descompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LiniaCompra> getLiniesCompra() {
        return liniesCompra;
    }

    public void setLiniesCompra(List<LiniaCompra> liniesCompra) {
        this.liniesCompra = liniesCompra;
    }

    public double getImportFinal() {
        return importFinal;
    }

    public void setImportFinal(double importFinal) {
        this.importFinal = importFinal;
    }

    public String getMetodePagament() {
        return metodePagament;
    }

    public void setMetodePagament(String metodePagament) {
        this.metodePagament = metodePagament;
    }

    public Double getDinersEntregats() {
        return dinersEntregats;
    }

    public void setDinersEntregats(Double dinersEntregats) {
        this.dinersEntregats = dinersEntregats;
    }

    public Double getDinersCanvi() {
        return dinersCanvi;
    }

    public void setDinersCanvi(Double dinersCanvi) {
        this.dinersCanvi = dinersCanvi;
    }

    public Double getDescompte() {
        return descompte;
    }

    public void setDescompte(Double descompte) {
        this.descompte = descompte;
    }
}

