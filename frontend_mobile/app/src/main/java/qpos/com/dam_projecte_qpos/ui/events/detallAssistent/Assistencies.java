package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.ui.events.Event;

public class Assistencies implements Serializable {
    private int id;
    @SerializedName("client")
    private Client client;
    @SerializedName("esdeveniment")
    private Event esdeveniment;

    public Assistencies() {
    }

    public Assistencies(Client client, Event esdeveniment) {
        this.client = client;
        this.esdeveniment = esdeveniment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Event getEsdeveniment() {
        return esdeveniment;
    }

    public void setEsdeveniment(Event esdeveniment) {
        this.esdeveniment = esdeveniment;
    }
}
