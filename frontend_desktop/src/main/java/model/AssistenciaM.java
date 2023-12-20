package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * Classe que representa una assistencia
 * @author Enric
 */
// Annotació per ignorar propietats desconegudes durant la deserialització JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistenciaM {

    /**
     * Atributs de la classe que representen les dades de l'assistencia
     */
    private ClientM client;
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
    private Date dataRegistre;
    private int esdeveniment;

    /**
     * Constructor buit per a la deserialització JSON
     */
    public AssistenciaM() {
    }

    /**
     * Get id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Get dataRegistre
     *
     * @return
     */
    public Date getDataRegistre() {
        return dataRegistre;
    }

    /**
     * Get Esdeveniment
     *
     * @return
     */
    public int getEsdeveniment() {
        return esdeveniment;
    }

    /**
     * Get Client
     *
     * @return
     */
    @JsonProperty("client")
    public ClientM getClient() {
        return client;
    }

    /**
     * Set Client
     *
     * @param client
     */
    @JsonProperty("client")
    public void setClient(ClientM client) {
        this.client = client;
    }

    /**
     * Override del mètode toString per obtenir una representació en cadena de
     * l'objecte
     *
     * @return
     */
    @Override
    public String toString() {
        return "AssistenciaM{"
                + "id=" + id
                + ", dataRegistre=" + dataRegistre
                + ", client=" + client
                + ", esdeveniment=" + esdeveniment
                + '}';
    }
}
