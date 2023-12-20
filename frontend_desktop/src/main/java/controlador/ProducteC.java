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
import model.ProducteM;
import model.nouProducteM;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb els productes
 * mitjançant crides a l'API
 *
 * @author Enric
 */
public class ProducteC {

    /**
     * Mètode per obtenir els productes mitjançant una crida GET a l'API
     *
     * @return Llista de productes
     */
    public ProducteM getProductes() {

        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Fes una crida GET per obtenir l'array de Producte a través de l'API
            String apiUrl = "https://qpos.onrender.com/api/productes?ordering=id";
            URL urlProductes = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlProductes.openConnection();
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
                    // Llegeix la resposta JSON i construeix un array de Producte
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();
                    ProducteM[] productes = objMapper.readValue(sb.toString(), new TypeReference<ProducteM[]>() {
                    });

                    // Retorna els productes com un model de ProducteM
                    return new ProducteM(productes);
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtenir les dades dels productes. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona els errors d'IO mostrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació dels productes", e);
        }

        // Retorna null si hi ha hagut problemes en l'execució
        return null;
    }

    /**
     * Mètode per afegir un producte mitjançant una crida POST a l'API
     *
     * @param producte El nou producte a afegir
     */
    public void afegeixProducte(nouProducteM producte) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Preparem URL per la petició POST
            URL urlProductes = new URL("https://qpos.onrender.com/api/productes/");
            HttpURLConnection conn = (HttpURLConnection) urlProductes.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix el objecte Producte a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String producteJson = objectMapper.writeValueAsString(producte);

            // Envia petició a la API
            try (DataOutputStream dos2 = new DataOutputStream(conn.getOutputStream())) {
                dos2.writeBytes(producteJson);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Producte afegit correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per eliminar un producte mitjançant una crida DELETE a l'API
     *
     * @param idProducte ID del producte a eliminar
     */
    public void eliminarProducte(int idProducte) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició DELETE
            URL urlProducte = new URL("https://qpos.onrender.com/api/productes/" + idProducte);
            HttpURLConnection conn = (HttpURLConnection) urlProducte.openConnection();
            conn.setRequestMethod("DELETE");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Producte eliminat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per editar un producte mitjançant una crida PUT a l'API
     *
     * @param idProducte ID del producte a editar
     * @param producte El producte editat
     */
    public void editarProducte(int idProducte, nouProducteM producte) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició PUT
            URL urlProducte = new URL("https://qpos.onrender.com/api/productes/" + idProducte + "/");
            HttpURLConnection conn = (HttpURLConnection) urlProducte.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix l'objecte Producte a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String producteJson = objectMapper.writeValueAsString(producte);

            // Escriu les dades JSON al flux de sortida
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = producteJson.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Producte editat correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per obtenir un producte mitjançant una crida GET a l'API amb un
     * identificador específic
     *
     * @param idproducte ID del producte a obtindre
     * @return El producte
     */
    public ProducteM getProducte(int idproducte) {
        try {
            // Obte l'instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Prepara la URL per a la petició GET
            URL urlProducte = new URL("https://qpos.onrender.com/api/productes/" + idproducte);
            HttpURLConnection conn = (HttpURLConnection) urlProducte.openConnection();
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
                    // Llegeix la resposta JSON i construeix un objecte ProducteM
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNext()) {
                        sb.append(scanner.nextLine());
                    }

                    ObjectMapper objMapper = new ObjectMapper();
                    ProducteM producte = objMapper.readValue(sb.toString(), ProducteM.class);

                    return producte;
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtenir les dades del producte. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona els errors d'IO mostrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació del producte", e);
        }

        // Retorna null si hi ha hagut problemes en l'execució
        return null;
    }
}
