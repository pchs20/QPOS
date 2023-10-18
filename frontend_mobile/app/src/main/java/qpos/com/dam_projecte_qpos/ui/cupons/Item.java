package qpos.com.dam_projecte_qpos.ui.cupons;

public class Item {
    private String codi;
    private String importe;
    private String data;

    public Item() {

    }
    public Item(String codi, String importe, String data) {
        this.codi = codi;
        this.importe = importe;
        this.data = data;
    }

    public String getCodi() {
        return codi;
    }

    public String getImporte() {
        return importe;
    }

    public String getData() {
        return data;
    }

}
