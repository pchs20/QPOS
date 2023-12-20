/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import vista.LoginV;
import vista.MenuV;

/**
 * Classe principal per iniciar l'aplicació de l'inici de sessió (Login)
 * @author Enric
 */
public class Main {

    /**
     * Punt d'entrada principal per l'aplicació de l'inici de sessió.
     *
     * @param args els arguments de la línia de comandes (no s'utilitzen en aquest cas)
     */
    public static void main(String[] args) {
        /* Estableix el "look and feel" Nimbus */
        //<editor-fold defaultstate="collapsed" desc=" Codi de configuració del look and feel (opcional) ">
        /* Si Nimbus (introduït a Java SE 6) no està disponible, es manté el "look and feel" predeterminat.
         * Per detalls, veure http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        // Crea una instància de la finestra de l'inici de sessió (LoginV)
        LoginV finestraLogin = new LoginV();

        // Estableix la posició de la finestra al centre de la pantalla
        finestraLogin.setLocationRelativeTo(null);

        // Fa visible la finestra de l'inici de sessió
        finestraLogin.setVisible(true);
    }
}
