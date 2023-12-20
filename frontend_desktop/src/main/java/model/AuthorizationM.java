package model;

/**
 * Classe que actua com a gestor del token d'autenticació (Singleton)
 * @author Enric
 */
public class AuthorizationM {

    /**
     * Atributs privats per emmagatzemar el nom d'usuari i el token
     */
    private String username;
    private String token;
    private int id;

    /**
     * Instància singleton
     */
    private static AuthorizationM instance;

    /**
     * Constructor privat
     */
    private AuthorizationM() {
    }

    /**
     * Mètode estàtic públic per obtenir la instància singleton
     * @return
     */
    public static AuthorizationM getInstance() {
        if (instance == null) {
            instance = new AuthorizationM();
        }
        return instance;
    }

    /**
     * Mètode getter d'username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Mètode setter d'username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Mètode getter de token
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Mètode setter de token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Override del mètode toString per obtenir una representació en cadena de l'objecte
     * @return
     */
    @Override
    public String toString() {
        return "Credentials{"
                + "username='" + username + '\''
                + ", token=" + token
                + '}';
    }

}
