package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import model.AuthorizationM;
import model.ClientM;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb els clients
 * mitjançant crides a l'API
 *
 * @author Enric
 */
public class ClientC {

    /**
     * Mètode per obtenir els clients mitjançant una crida GET a l'API
     *
     * @return Els clients
     */
    public ClientM getClients() {

        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Fes una crida GET per obtenir l'array de clients a través de l'API
            String apiUrl = "https://qpos.onrender.com/api/clients/";
            URL urlClients = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlClients.openConnection();
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
                    // Llegeix la resposta JSON i construeix un array de Client
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();
                    ClientM[] clients = objMapper.readValue(sb.toString(), new TypeReference<ClientM[]>() {
                    });

                    // Retorna els clients com un model de ClientM
                    return new ClientM(clients);
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtenir les dades dels clients. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona els errors d'IO mostrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació dels clients", e);
        }

        // Retorna null si hi ha hagut problemes en l'execució
        return null;
    }

    /**
     * Mètode per obtenir un client mitjançant una crida GET a l'API amb un
     * identificador específic
     *
     * @param dni El dni del client
     * @return El client
     */
    public ClientM getClientPerDNI(String dni) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició GET
            URL urlClient = new URL("https://qpos.onrender.com/api/clients?usuari__dni=" + dni);
            HttpURLConnection conn = (HttpURLConnection) urlClient.openConnection();
            conn.setRequestMethod("GET");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();
                    ClientM[] clients = objMapper.readValue(sb.toString(), ClientM[].class);

                    // Retorna el primer client si l'array no està buit
                    return clients.length > 0 ? clients[0] : null;
                }
            } else {
                // Gestiona HTTP error codes
                System.out.println("Error: HTTP response code " + responseCode);
                return null;
            }
        } catch (IOException e) {
            // Gestiona IO exceptions
            e.printStackTrace();
            return null;
        }
    }

}
