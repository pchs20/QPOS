package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.swing.JOptionPane;

/**
 * GestorErrors class és responsable de gestionar i registrar errors. Conté
 * mètodes per a la gestió i visualització d'errors, així com la manipulació
 * d'errors específics d'HTTP i IO.
 *
 * @author Enric
 */
public class GestorErrors {

    /**
     * Registra un error amb el missatge proporcionat i l'excepció associada, si
     * hi ha.
     *
     * @param message El missatge d'error.
     * @param exception L'excepció associada a l'error. Pot ser null.
     */
    public static void logError(String message, Exception exception) {
        System.err.println("Error: " + message);
        if (exception != null) {
            exception.printStackTrace();
        }
    }

    /**
     * Mostra un missatge d'error a través d'una finestra emergent i el
     * registra.
     *
     * @param message El missatge d'error a mostrar.
     */
    public static void displayError(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.err.println("Error: " + message);
    }

    /**
     * Mostra un missatge a través d'una finestra emergent.
     *
     * @param message El missatge d'error a mostrar.
     */
    public static void mostraMissatge(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Gestiona errors HTTP a partir d'una connexió HttpURLConnection
     * proporcionada. Mostra un missatge d'error amb el codi de resposta HTTP.
     *
     * @param conn La connexió HttpURLConnection amb l'error HTTP.
     */
    public static void handleHttpError(HttpURLConnection conn) {
        int responseCode;
        try {
            responseCode = conn.getResponseCode();
            JOptionPane.showMessageDialog(null, "HTTP Error: " + responseCode);
            System.err.println("HTTP Error: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestiona errors d'entrada/sortida (IO) imprimint la traça de l'excepció.
     *
     * @param e L'excepció d'entrada/sortida a gestionar.
     */
    public static void handleIOException(IOException e) {
        e.printStackTrace();
    }
}
