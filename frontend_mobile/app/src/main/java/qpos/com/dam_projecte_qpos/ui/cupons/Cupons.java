package qpos.com.dam_projecte_qpos.ui.cupons;

import java.io.Serializable;

public class Cupons implements Serializable {
    private int id;
    private String nom;
    private String descripcio;
    private int punts;
    private String descompte;
    private String dataFinal;

    public Cupons() {

    }

    public Cupons(int codi, String nom, String descripcio, int punts, String importe, String data) {
        this.id = codi;
        this.nom = nom;
        this.descripcio = descripcio;
        this.punts = punts;
        this.descompte = importe;
        this.dataFinal = data;
    }

    public int getId() {
        return id;
    }

    public String getDescompte() {
        return descompte;
    }

    public String getDataFinal() {
        return dataFinal;
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

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public void setDescompte(String descompte) {
        this.descompte = descompte;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}
