package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 * Classe que representa una compra
 * 
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraM {

    /**
     * Atributs de la classe que representen les dades de la compra
     */
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm:ss")
    private Date data;

    private ClientM client;
    private TreballadorM treballador;
    private LiniaCompraM[] liniesCompra;

    private double importFinal;
    private String metodePagament;
    private double dinersEntregats;
    private double dinersCanvi;
    private double descompte;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public CompraM() {
    }

    // Mètodes getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ClientM getClient() {
        return client;
    }

    public void setClient(ClientM client) {
        this.client = client;
    }

    public TreballadorM getTreballador() {
        return treballador;
    }

    public void setTreballador(TreballadorM treballador) {
        this.treballador = treballador;
    }

    public LiniaCompraM[] getLiniesCompra() {
        return liniesCompra;
    }

    public void setLiniesCompra(LiniaCompraM[] liniesCompra) {
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

    public double getDinersEntregats() {
        return dinersEntregats;
    }

    public void setDinersEntregats(double dinersEntregats) {
        this.dinersEntregats = dinersEntregats;
    }

    public double getDinersCanvi() {
        return dinersCanvi;
    }

    public void setDinersCanvi(double dinersCanvi) {
        this.dinersCanvi = dinersCanvi;
    }

    public double getDescompte() {
        return descompte;
    }

    public void setDescompte(double descompte) {
        this.descompte = descompte;
    }
}

