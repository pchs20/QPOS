package qpos.com.dam_projecte_qpos.ui.factures;

import java.io.Serializable;

public class Producte implements Serializable {
    private int id;
    private String nom;
    private Double preu;

    public Producte(int id, int proveidor, String nom,Double preu) {
        this.id= id;
        this.nom = nom;
        this.preu = preu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
        this.preu = preu;
    }
}
