package controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.AuthorizationM;
import model.CompraM;
import model.LiniaCompraM;
import model.novaCompraM;
import org.apache.commons.lang3.StringUtils;
import util.GestorErrors;

/**
 * Classe encarregada de gestionar les operacions relacionades amb les compres
 *
 * @author Enric
 */
public class CompraC {

    /**
     * Mètode per crear una nova compra
     *
     * @param compra La nova compra
     */
    public void creaCompra(novaCompraM compra) {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Preparem URL per la petició POST
            URL urlCompras = new URL("https://qpos.onrender.com/api/compres/");
            HttpURLConnection conn = (HttpURLConnection) urlCompras.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            // Converteix el objecte Compra a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String compraJson = objectMapper.writeValueAsString(compra);

            System.out.println(compra);

            // Envia petició a la API
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(compraJson);
            }

            System.out.println(compraJson);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Compra creada correctament");
            } else {
                GestorErrors.handleHttpError(conn);
            }
        } catch (IOException e) {
            GestorErrors.handleIOException(e);
        }
    }

    /**
     * Mètode per llistat totes les compres
     *
     * @return La llista de les compres
     */
    public List<CompraM> getCompres() {
        try {
            // Obté la instància de la classe AuthorizationM (gestora de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtinguem el token d'autenticació
            String token = authInstance.getToken();

            // Preparem URL per la petició GET
            String apiUrl = "https://qpos.onrender.com/api/compres/";
            URL urlCompres = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlCompres.openConnection();
            conn.setRequestMethod("GET");

            // Estableix l'autorització mitjançant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Use try-with-resources to manage the automatic closure of the Scanner
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        response.append(line).append("\n");
                    }

                    ObjectMapper objMapper = new ObjectMapper();

                    List<CompraM> compres = objMapper.readValue(response.toString(), new TypeReference<List<CompraM>>() {
                    });

                    // Retorna la llista de novaCompraM
                    return compres;
                }
            } else {
                // En cas d'error en la crida, mostra un missatge d'error
                GestorErrors.displayError("No s'ha pogut obtindre les dades de les compres. HTTP Error Code: " + responseCode);
            }
        } catch (IOException e) {
            // Gestiona IO errors
            GestorErrors.logError("IO Exception durant la recuperació de les compres", e);
        }

        // Retorna una llista buida si hi ha hagut problemes en l'execució
        return Collections.emptyList();
    }

    /**
     * Mètode per llistar les compres amb filtres
     *
     * @param clientID Identificador del client que ha fet la compra
     * @param employeeID Identificador de l'empleat que ha atès al client
     * @param paymentMethod Mètode de pagament
     * @return Llista de compres que compleixen els filtres especificats
     */
    public List<CompraM> getCompresAmbFiltres(String clientID, String employeeID, String paymentMethod) {
        try {
            // Obtenir una instància de la classe AuthorizationM (gestor de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtenir el token d'autenticació
            String token = authInstance.getToken();

            // Construir la URL base
            String apiUrl = "https://qpos.onrender.com/api/compres/";

            // Construir els paràmetres de la consulta basats en l'entrada de l'usuari
            StringBuilder queryParams = new StringBuilder();

            if (!StringUtils.isBlank(clientID)) {
                queryParams.append("client__usuari__user__id=").append(clientID);
            }

            if (!StringUtils.isBlank(employeeID)) {
                if (queryParams.length() > 0) {
                    queryParams.append("&");
                }
                queryParams.append("treballador__usuari__user__id=").append(employeeID);
            }

            if (!StringUtils.isBlank(paymentMethod)) {
                if (queryParams.length() > 0) {
                    queryParams.append("&");
                }
                queryParams.append("metodePagament=").append(paymentMethod);
            }

            // Combinar la URL base i els paràmetres de la consulta
            if (queryParams.length() > 0) {
                apiUrl += "?" + queryParams.toString();
            }

            URL urlCompres = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) urlCompres.openConnection();
            conn.setRequestMethod("GET");

            // Establir l'autorització utilitzant el token obtingut
            conn.setRequestProperty("Authorization", "Token " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Utilitzar try-with-resources per gestionar el tancament automàtic del Scanner
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        response.append(line).append("\n");
                    }

                    // Imprimir la resposta JSON en brut
                    System.out.println("Resposta JSON en brut:");
                    System.out.println(response.toString());

                    ObjectMapper objMapper = new ObjectMapper();

                    List<CompraM> compres = objMapper.readValue(response.toString(), new TypeReference<List<CompraM>>() {
                    });

                    // Retornar la llista de compres
                    return compres;
                }
            } else {
                // En cas d'error en la crida, mostrar un missatge d'error
                GestorErrors.displayError("No s'ha pogut recuperar les dades de compra. Codi d'error HTTP: " + responseCode);
            }
        } catch (IOException e) {
            // Gestionar els errors d'IO registrant-los a la consola
            GestorErrors.logError("Excepció d'IO durant la recuperació de compres", e);
        }

        // Retornar una llista buida si hi ha problemes durant l'execució
        return Collections.emptyList();
    }

    /**
     * Mètode que retorna una compra per ID
     * 
     * @param compraID Identificador de la compra
     * @return Objecte CompraM corresponent a l'ID especificat
     */
    public CompraM getCompraPerID(String compraID) {
        try {
            // Obtenir una instància de la classe AuthorizationM (gestor de tokens)
            AuthorizationM authInstance = AuthorizationM.getInstance();

            // Obtenir el token d'autenticació
            String token = authInstance.getToken();

            // Preparar la URL per a la sol·licitud GET
            URL urlCompra = new URL("https://qpos.onrender.com/api/compres/" + compraID);
            HttpURLConnection conn = (HttpURLConnection) urlCompra.openConnection();
            conn.setRequestMethod("GET");

            // Establir l'autorització utilitzant el token obtingut
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
                    return objMapper.readValue(sb.toString(), CompraM.class);
                }
            } else {
                // Gestionar els codis d'error HTTP
                System.out.println("Error: Codi de resposta HTTP " + responseCode);
                return null;
            }
        } catch (IOException e) {
            // Gestionar les excepcions d'IO
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Imprimeix les compres proporcionades a la consola
     *
     * @param compras Llista de compres a imprimir
     */
    public void printCompras(List<CompraM> compras) {
        for (CompraM compra : compras) {
            System.out.println("ID de la Compra: " + compra.getId());
            System.out.println("Data: " + compra.getData());
            System.out.println("ID del Client: " + compra.getClient().getNom() + " " + compra.getClient().getCognoms());
            System.out.println("ID del Treballador: " + compra.getTreballador().getNom() + " " + compra.getTreballador().getCognoms());
            System.out.println("Mètode de Pagament: " + compra.getMetodePagament());

            // Imprimeix les Línies
            LiniaCompraM[] linies = compra.getLiniesCompra();

            // Comprova si les línies no són nul·les abans de iterar
            if (linies != null) {
                System.out.println("Línies de la Compra:");
                for (LiniaCompraM linia : linies) {
                    System.out.println("  Producte: " + linia.getProducte().getNom());
                    System.out.println("  Preu: " + linia.getProducte().getPreu());
                    System.out.println("  Quantitat: " + linia.getQuantitat());
                }
            }

            System.out.println("Import Final: " + compra.getImportFinal());

            System.out.println("------------------------------------");
        }
    }

}
