package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.AssistenciaM;
import model.AuthorizationM;
import model.ClientM;
import model.CreadorM;
import model.EsdevenimentM;
import model.nouEsdevenimentM;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb els
 * esdeveniments
 *
 * @author Enric
 */
public class EsdevenimentC {

    /**
     * Mètode que retorna la llista d'esdeveniments
     *
     * @return La llista d'esdeveniments
     */
    public List<EsdevenimentM> getEsdeveniments() {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Fes una crida GET per obtenir l'array d'Esdeveniment a través de l'API
            String apiUrl = "https://qpos.onrender.com/api/esdeveniments/";
            URL urlEsdeveniments = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlEsdeveniments.openConnection();
            conn.setRequestMethod("GET");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Utilitza try-with-resources per gestionar el tancament automàtic del Scanner
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    // Llegeix la resposta JSON i construeix una llista d'EsdevenimentM
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();

                    List<EsdevenimentM> esdeveniments = objMapper.readValue(sb.toString(), new TypeReference<List<EsdevenimentM>>() {
                    });

                    // Retorna la llista d'EsdevenimentM
                    return esdeveniments;
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtenir les dades dels esdeveniments. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona els errors d'IO mostrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació dels esdeveniments", e);
        }

        // Retorna una llista buida si hi ha hagut problemes en l'execució
        return Collections.emptyList();
    }

    /**
     * Mètode que retorna una llista d'esdeveniments ordenats segons un criteri
     *
     * @param criteri El criteri amb el que ordenarem els esdeveniments
     * @return La llista ordenada segons el criteri
     */
    public List<EsdevenimentM> getEsdevenimentsPerOrdre(String criteri) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Fes una crida GET per obtenir l'array d'Esdeveniment a través de l'API
            String apiUrl = "https://qpos.onrender.com/api/esdeveniments?ordering=" + criteri;
            URL urlEsdeveniments = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlEsdeveniments.openConnection();
            conn.setRequestMethod("GET");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Utilitza try-with-resources per gestionar el tancament automàtic del Scanner
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    // Llegeix la resposta JSON i construeix una llista d'EsdevenimentM
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();

                    List<EsdevenimentM> esdeveniments = objMapper.readValue(sb.toString(), new TypeReference<List<EsdevenimentM>>() {
                    });

                    // Retorna la llista d'EsdevenimentM
                    return esdeveniments;
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtenir les dades dels esdeveniments. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona els errors d'IO mostrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació dels esdeveniments", e);
        }

        // Retorna una llista buida si hi ha hagut problemes en l'execució
        return Collections.emptyList();
    }

    /**
     * Mètode per afegir un esdeveniment mitjançant una crida POST a l'API
     *
     * @param esdeveniment El nou esdeveniment a afegir
     */
    public void afegeixEsdeveniment(nouEsdevenimentM esdeveniment) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Preparem URL per la petició POST
            URL urlProductes = new URL("https://qpos.onrender.com/api/esdeveniments/");
            HttpURLConnection conn = (HttpURLConnection) urlProductes.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix el objecte Esdeveniment a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String producteJson = objectMapper.writeValueAsString(esdeveniment);

            // Envia petició a la API
            try (DataOutputStream dos2 = new DataOutputStream(conn.getOutputStream())) {
                dos2.writeBytes(producteJson);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Esdeveniment afegit correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Imprimeix els esdeveniments proporcionats a la consola
     *
     * @param esdeveniments Llista d'esdeveniments a imprimir
     */
    public void printEsdeveniments(List<EsdevenimentM> esdeveniments) {
        for (EsdevenimentM esdeveniment : esdeveniments) {
            System.out.println("ID: " + esdeveniment.getId());
            System.out.println("Nom: " + esdeveniment.getNom());
            System.out.println("Descripció: " + esdeveniment.getDescripcio());
            System.out.println("Data de Creació: " + esdeveniment.getDataCreacio());
            System.out.println("Data: " + esdeveniment.getData());
            System.out.println("Aforament: " + esdeveniment.getAforament());
            System.out.println("Durada: " + esdeveniment.getDurada());
            System.out.println("Ubicació: " + esdeveniment.getUbicacio());

            // Imprimir dades del creador
            CreadorM creador = esdeveniment.getCreador();
            System.out.println("Creador:");
            System.out.println("  ID: " + creador.getId());
            System.out.println("  User: " + creador.getUser());
            // Altres propietats del creador...

            // Imprimir dades d'assistències
            List<AssistenciaM> assistencies = esdeveniment.getAssistencies();
            System.out.println("Assistències:");
            for (AssistenciaM assistencia : assistencies) {
                System.out.println("  Client:");
                ClientM client = assistencia.getClient();
                System.out.println("    ID: " + client.getId());
                System.out.println("    User: " + client.getUser());
                System.out.println("    Telèfon: " + client.getTelefon());
                // Altres propietats del client...
            }

            System.out.println("------------------------------------");
        }
    }

    /**
     * Mètode per eliminar un esdeveniment mitjançant una crida DELETE a l'API
     *
     * @param idEsdeveniment El id de l'esdeveniment a eliminar
     */
    public void eliminarEsdeveniment(int idEsdeveniment) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició DELETE
            URL urlProducte = new URL("https://qpos.onrender.com/api/esdeveniments/" + idEsdeveniment);
            HttpURLConnection conn = (HttpURLConnection) urlProducte.openConnection();
            conn.setRequestMethod("DELETE");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Esdeveniment eliminat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per editar un esdeveniment mitjançant una crida PUT a l'API
     *
     * @param idEsdeveniment ID de l'esdeveniment a editar
     * @param esdeveniment Esdeveniment editat
     */
    public void editarEsdeveniment(int idEsdeveniment, nouEsdevenimentM esdeveniment) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició PUT
            URL urlEsdeveniment = new URL("https://qpos.onrender.com/api/esdeveniments/" + idEsdeveniment + "/");
            HttpURLConnection conn = (HttpURLConnection) urlEsdeveniment.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix l'objecte Esdeveniment a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String esdevenimentJson = objectMapper.writeValueAsString(esdeveniment);

            // Escriu les dades JSON al flux de sortida
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = esdevenimentJson.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Esdeveniment editat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }
}
