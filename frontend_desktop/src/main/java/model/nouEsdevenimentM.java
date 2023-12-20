package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Classe que representa un nou esdeveniment
 *
 * @author Enric
 */
public class nouEsdevenimentM {

    /**
     * Atributs de la classe que representen les dades del nou esdeveniment
     */
    private String nom;
    private String descripcio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm:ss")
    private Date data;

    private int aforament;
    private String durada;
    private String ubicacio;
    private int creador_id;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public nouEsdevenimentM() {
    }

    /**
     * Constructor amb paràmetres
     *
     * @param nom
     * @param descripcio
     * @param data
     * @param aforament
     * @param durada
     * @param ubicacio
     * @param creador_id
     */
    public nouEsdevenimentM(String nom, String descripcio, Date data, int aforament, String durada, String ubicacio, int creador_id) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.data = data;
        this.aforament = aforament;
        this.durada = durada;
        this.ubicacio = ubicacio;
        this.creador_id = creador_id;
    }

    // Mètodes getters i setters
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAforament() {
        return aforament;
    }

    public void setAforament(int aforament) {
        this.aforament = aforament;
    }

    public String getDurada() {
        return durada;
    }

    public void setDurada(String durada) {
        this.durada = durada;
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public int getCreador_id() {
        return creador_id;
    }

    public void setCreador_id(int creador_id) {
        this.creador_id = creador_id;
    }
}
