package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import model.AuthorizationM;
import model.TreballadorM;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb els
 * treballadors
 *
 * @author Enric
 */
public class TreballadorC {

    /**
     * Mètode per obtenir un treballador mitjançant una crida GET a l'API amb un
     * identificador específic
     *
     * @param id ID del treballador
     * @return El treballador
     */
    public TreballadorM getTreballador(int id) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició GET
            URL urlClient = new URL("https://qpos.onrender.com/api/treballadors/" + id);
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
                    TreballadorM treballador = objMapper.readValue(sb.toString(), TreballadorM.class);

                    return treballador;

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

    /**
     * Mètode per obtenir tots els treballadors mitjançant una crida GET a l'API
     *
     * @return Una llista de TreballadorM o null si hi ha algun error
     */
    public List<TreballadorM> getTreballadors() {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició GET
            URL urlClient = new URL("https://qpos.onrender.com/api/treballadors");
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
                    List<TreballadorM> treballadors = objMapper.readValue(sb.toString(), new TypeReference<List<TreballadorM>>() {
                    });

                    return treballadors;
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

    /**
     * Mètode per afegir un treballador mitjançant una crida POST a l'API
     *
     * @param treballador El nou treballador a afegir
     */
    public void afegeixTreballador(TreballadorM treballador) {
        try {

            // Preparem URL per la petició POST
            URL urlProductes = new URL("https://qpos.onrender.com/api/signup/treballadors/");
            HttpURLConnection conn = (HttpURLConnection) urlProductes.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix el objecte Treballador a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String treballadorJson = objectMapper.writeValueAsString(treballador);

            System.out.println(treballadorJson);

            // Envia petició a la API
            try (DataOutputStream dos2 = new DataOutputStream(conn.getOutputStream())) {
                dos2.writeBytes(treballadorJson);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Treballador afegit correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per editar un treballador mitjançant una crida PUT a l'API
     *
     * @param idTreballador ID del treballador a editar
     * @param treballador El treballador editat
     */
    public void editaTreballador(int idTreballador, TreballadorM treballador) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició PUT
            URL urlTreballador = new URL("https://qpos.onrender.com/api/treballadors/" + idTreballador + "/");
            HttpURLConnection conn = (HttpURLConnection) urlTreballador.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix l'objecte Treballador a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String treballadorJson = objectMapper.writeValueAsString(treballador);

            // Envia petició a la API
            try (DataOutputStream dos2 = new DataOutputStream(conn.getOutputStream())) {
                dos2.writeBytes(treballadorJson);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Treballador editat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

}
