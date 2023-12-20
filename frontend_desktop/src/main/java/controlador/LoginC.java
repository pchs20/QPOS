package controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import model.AuthorizationM;
import model.UsuariM;
import vista.MenuV;

/**
 * Classe encarregada de gestionar les operacions relacionades amb l'usuari, com ara el login
 * 
 * @author Enric
 */
public class LoginC {

    /**
     * Mètode per iniciar la sessió d'un usuari
     * 
     * @param username
     * @param password
     * @param isAdmin
     * @throws IOException
     */
    public void loginUser(String username, char[] password, boolean isAdmin) throws IOException {
        try {
            // Converteix el password de tipus char[] a String
            String passwordString = String.valueOf(password);
            String apiURL;

            // Crea un objecte UsuariM amb les dades d'usuari
            UsuariM usuariLogin = new UsuariM(username, passwordString);
            ObjectMapper objM1 = new ObjectMapper();
            String requestBodyLogin = objM1.writeValueAsString(usuariLogin);

            // Determina l'URL de l'API en funció si l'usuari és administrador o no
            apiURL = isAdmin ? "https://qpos.onrender.com/api/login/admins/" : "https://qpos.onrender.com/api/login/treballadors/";
            usuariLogin.setIsAdmin(isAdmin);

            // Crea una connexió HTTPS a l'API per realitzar el login
            URL urlLogin = new URL(apiURL);
            HttpsURLConnection conn = (HttpsURLConnection) urlLogin.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // Envia la sol·licitud POST amb les dades d'usuari
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(requestBodyLogin);
            }

            // Obté la resposta del servidor
            int responseCode = conn.getResponseCode();
            AuthorizationC authC = new AuthorizationC();

            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                // Gestiona el login amb èxit
                handleSuccessfulLogin(usuariLogin);

                // Extrau el token de la resposta i l'afegeix a la gestió global del token
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }

                AuthorizationM authInstance = AuthorizationM.getInstance();
                authInstance.setToken(authC.getAuthToken(String.valueOf(sb)));
                authInstance.setId(authC.getAuthId(String.valueOf(sb)));
            } else {
                // Gestiona els errors de login
                handleLoginError();
            }

        } catch (JsonProcessingException ex) {
            // Registra els errors de processament JSON
            Logger.getLogger(LoginC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            // Registra errors d'URL malformada
            Logger.getLogger(LoginC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // Registra altres errors d'IO
            Logger.getLogger(LoginC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mètode per gestionar el login amb èxit
     * 
     * @param user
     */
    private void handleSuccessfulLogin(UsuariM user) {
        // Obre el menú principal i estableix la sessió
        MenuV m = new MenuV();
        
        m.setSessio(user.getUsername(), user.getIsAdmin());
        m.setVisible(true);
    }

    /**
     * Mètode per gestionar els errors de login
     */
    private void handleLoginError() {
        // Imprimeix un missatge d'error a la consola i mostra un dialeg amb l'usuari
        System.out.println("Error");
        JOptionPane.showMessageDialog(null, "Dades incorrectes");
    }
}
