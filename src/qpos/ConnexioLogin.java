package qpos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Enric
 */
public class ConnexioLogin {

    public void validaUsuari(JTextField usuari, JPasswordField password) {

        try {
            ResultSet rs = null;
            PreparedStatement ps = null;

            ConnexioBD c = new ConnexioBD();

            String consulta = "SELECT * FROM usuaris WHERE usuaris.usuari =(?) AND usuaris.password=(?);";
            ps = ConnexioBD.mycon().prepareStatement(consulta);

            String contrasenya = String.valueOf(password.getPassword());

            ps.setString(1, usuari.getText());
            ps.setString(2, contrasenya);

            rs = ps.executeQuery();

            if (rs.next()) {
                Menu m = new Menu();
                m.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuari o contrasenya icorrecta");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
        }
    }

}
