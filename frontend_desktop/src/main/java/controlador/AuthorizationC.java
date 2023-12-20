package controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AuthorizationM;

/**
 * Classe encarregada de processar la resposta de l'autenticació
 * 
 * @author Enric
 */
public class AuthorizationC {

    /**
     * Mètode per obtenir el token d'autenticació a partir del contingut de la resposta
     * @param responseContent
     * @return
     * @throws IOException
     */
    public String getAuthToken(String responseContent) throws IOException {
        try {
            // Objecte ObjectMapper converteix el contingut de la resposta a un objecte AuthorizationM
            ObjectMapper objM = new ObjectMapper();
            AuthorizationM auth = objM.readValue(responseContent, new TypeReference<AuthorizationM>() {
            });

            // Retorna el token obtingut de l'objecte AuthorizationM
            return auth.getToken();

        } catch (JsonProcessingException ex) {
            // Gestiona l'error de processament de JSON
            Logger.getLogger(AuthorizationC.class.getName()).log(Level.SEVERE, null, ex);
            
            // Retorna null en cas d'error
            return null;
        }
    }
    
    /**
     * Mètode per obtenir el ID del usuari a partir del contingut de la resposta
     * @param responseContent
     * @return
     * @throws IOException
     */
    public int getAuthId(String responseContent) throws IOException {
        try {
            // Objecte ObjectMapper converteix el contingut de la resposta a un objecte AuthorizationM
            ObjectMapper objM = new ObjectMapper();
            AuthorizationM auth = objM.readValue(responseContent, new TypeReference<AuthorizationM>() {
            });

            // Retorna el ID obtingut de l'objecte AuthorizationM
            return auth.getId();

        } catch (JsonProcessingException ex) {
            // Gestiona l'error de processament de JSON
            Logger.getLogger(AuthorizationC.class.getName()).log(Level.SEVERE, null, ex);
            
            // Retorna null en cas d'error
            return 0;
        }
    }
    
}
