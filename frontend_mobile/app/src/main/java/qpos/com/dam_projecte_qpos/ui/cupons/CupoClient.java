package qpos.com.dam_projecte_qpos.ui.cupons;

import qpos.com.dam_projecte_qpos.Client;

public class CupoClient {
    private int id;
    private int quantitat;
    private int client;
    private int cupo;

    public CupoClient(int id, int quantitat, int client, int cupo) {
        this.id = id;
        this.quantitat = quantitat;
        this.client = client;
        this.cupo = cupo;
    }
    public CupoClient( int quantitat, int client, int cupo) {
        this.quantitat = quantitat;
        this.client = client;
        this.cupo = cupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}
