package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que gestiona el format de les dates
 *
 * @author Enric
 */
public class DateUtils {

    /**
     * Atribut que determina el format de la data
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    /**
     * Mètode que retorna la data en el format determinat
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return DATE_FORMAT.format(date);
    }
}
