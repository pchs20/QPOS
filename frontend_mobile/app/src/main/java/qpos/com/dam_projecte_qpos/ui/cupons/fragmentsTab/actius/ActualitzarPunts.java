package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius;

public class ActualitzarPunts {

    private int client;

    private int punts;

    public ActualitzarPunts(int client, int punts) {
        this.client = client;
        this.punts = punts;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }
}
