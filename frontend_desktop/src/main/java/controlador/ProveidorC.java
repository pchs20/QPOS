package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import model.AuthorizationM;
import model.ProveidorM;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb els
 * proveïdors
 * 
 * @author Enric
 */
public class ProveidorC {

    /**
     * Mètode que retorna la llista de proveïdors
     * 
     * @return La llista de proveïdors
     */
    public ProveidorM getProveidors() {
        try {
            AuthorizationM authInstance = AuthorizationM.getInstance();
            String token = authInstance.getToken();

            String apiUrl = "https://qpos.onrender.com/api/proveidors/";
            URL urlProveidors = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlProveidors.openConnection();
            conn.setRequestMethod("GET");

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
                    ProveidorM[] proveidors = objMapper.readValue(sb.toString(), new TypeReference<ProveidorM[]>() {
                    });

                    return new ProveidorM(proveidors);
                }
            } else {
                GestorErrors.displayError("No s'ha pogut obtenir les dades dels proveidors. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            GestorErrors.logError("Excepció d'IO durant la recuperació dels proveidors", e);
        }

        return null;
    }

    /**
     * Mètode per afegir un proveïdor mitjançant una crida POST a l'API
     * 
     * @param proveidor El nou proveïdor a afegir
     */
    public void afegeixProveidor(ProveidorM proveidor) {
        try {
            AuthorizationM authInstance = AuthorizationM.getInstance();
            String token = authInstance.getToken();

            URL urlProveidors = new URL("https://qpos.onrender.com/api/proveidors/");
            HttpURLConnection conn = (HttpURLConnection) urlProveidors.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String proveidorJson = objectMapper.writeValueAsString(proveidor);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(proveidorJson);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Proveidor afegit correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per editar un proveïdor mitjançant una crida PUT a l'API
     * 
     * @param idProveidor La ID del proveïdor a editar
     * @param proveidor El proveïdor editat
     */
    public void editarProveidor(int idProveidor, ProveidorM proveidor) {
        try {
            AuthorizationM authInstance = AuthorizationM.getInstance();
            String token = authInstance.getToken();

            URL urlProveidor = new URL("https://qpos.onrender.com/api/proveidors/" + idProveidor + "/");
            HttpURLConnection conn = (HttpURLConnection) urlProveidor.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String proveidorJson = objectMapper.writeValueAsString(proveidor);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = proveidorJson.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Proveidor editat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per obtindre un proveïdor per ID mitjançant una crida GET a l'API
     * 
     * @param idProveidor ID del proveïdor
     * @return El proveïdor
     */
    public ProveidorM getProveidor(int idProveidor) {
        try {
            AuthorizationM authInstance = AuthorizationM.getInstance();
            String token = authInstance.getToken();

            URL urlProveidor = new URL("https://qpos.onrender.com/api/proveidors/" + idProveidor);
            HttpURLConnection conn = (HttpURLConnection) urlProveidor.openConnection();
            conn.setRequestMethod("GET");

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
                    ProveidorM proveidor = objMapper.readValue(sb.toString(), ProveidorM.class);

                    return proveidor;
                }
            } else {
                GestorErrors.displayError("No s'ha pogut obtenir les dades del proveidor. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            GestorErrors.logError("Excepció d'IO durant la recuperació del proveidor", e);
        }

        return null;
    }
}
