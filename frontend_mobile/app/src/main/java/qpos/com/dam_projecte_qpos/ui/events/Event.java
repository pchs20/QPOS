package qpos.com.dam_projecte_qpos.ui.events;

import java.io.Serializable;
import java.util.List;

import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.Assistencies;

public class Event implements Serializable {

    private int id;
    private String nom;
    private String ubicacio;
    private String data;
    private String aforament;
    private String durada;
    private String descripcio;
    private Creator creador;
    private List<Assistencies> assistencies;

    public Event() {
    }

    public Event(int id, String nom, String ubicacio, String aforament, String durada, String descripcio, List<Assistencies> assistencies, Creator creador) {
        this.id = id;
        this.nom = nom;
        this.ubicacio = ubicacio;
        this.aforament = aforament;
        this.durada = durada;
        this.descripcio = descripcio;
        this.assistencies = assistencies;
        this.creador = creador;
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

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAforament() {
        return aforament;
    }

    public void setAforament(String aforament) {
        this.aforament = aforament;
    }

    public String getDurada() {
        return durada;
    }

    public void setDurada(String durada) {
        this.durada = durada;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Creator getCreador() {
        return creador;
    }

    public void setCreador(Creator creador) {
        this.creador = creador;
    }

    public List<Assistencies> getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(List<Assistencies> assistencies) {
        this.assistencies = assistencies;
    }

    @Override
    public String toString() {
        return "Event{id=" + id + ", nom='" + nom + "', ubicacio='" + ubicacio + "', assistencies=" + assistencies.size() + "}";
    }
}

