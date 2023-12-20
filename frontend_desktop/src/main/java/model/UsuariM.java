package model;

/**
 * Classe que representa un usuari
 *
 * @author Enric
 */
public class UsuariM {

    /**
     * Atributs de la classe que representen les dades del usuari
     */
    private String username;
    private String password;
    private String token;
    private Boolean isAdmin;

    /**
     * Constructor amb paràmetres per establir el nom d'usuari i la contrasenya
     *
     * @param username
     * @param password
     */
    public UsuariM(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor buit per a la deserialització JSON Pot ser utilitzat pel
     * mapeig de les respostes del servidor a aquest objecte
     */
    public UsuariM() {
    }

    /**
     * Get username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get token
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Set token
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get isAdmin
     *
     * @return
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set isAdmin
     *
     * @param isAdmin
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
