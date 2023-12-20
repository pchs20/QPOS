package vista;

import controlador.LoginC;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa la pantalla del login
 * @author Enric
 */
public class LoginV extends javax.swing.JFrame {

    /**
     * Instància del controlador d'usuari
     */
    private final LoginC userC;
    
    /**
     * Constructor de la classe
     */
    public LoginV() {
        initComponents();
        this.userC = new LoginC();
    }
    
    /**
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pantalla = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        iconoUsuari2 = new javax.swing.JLabel();
        usuariText = new javax.swing.JTextField();
        iconoUsuari = new javax.swing.JLabel();
        iconoPassword = new javax.swing.JLabel();
        passwordText = new javax.swing.JPasswordField();
        botoLogin = new javax.swing.JButton();
        isAdminRadioButton = new javax.swing.JRadioButton();
        botoRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(43, 45, 66));

        Pantalla.setBackground(new java.awt.Color(43, 45, 66));

        loginPanel.setBackground(new java.awt.Color(43, 45, 66));

        iconoUsuari2.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 36)); // NOI18N
        iconoUsuari2.setForeground(new java.awt.Color(237, 242, 244));
        iconoUsuari2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoUsuari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/qpos.png"))); // NOI18N
        iconoUsuari2.setText("QPOS");
        iconoUsuari2.setToolTipText("");
        iconoUsuari2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iconoUsuari2.setIconTextGap(10);
        iconoUsuari2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        usuariText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        usuariText.setForeground(new java.awt.Color(141, 153, 174));
        usuariText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuariText.setText("NOM D'USUARI");
        usuariText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuariTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuariTextFocusLost(evt);
            }
        });

        iconoUsuari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/usuari.png"))); // NOI18N

        iconoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/password.png"))); // NOI18N

        passwordText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        passwordText.setForeground(new java.awt.Color(141, 157, 174));
        passwordText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordText.setText("Password");
        passwordText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordTextFocusLost(evt);
            }
        });

        botoLogin.setBackground(new java.awt.Color(141, 153, 174));
        botoLogin.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        botoLogin.setForeground(new java.awt.Color(43, 45, 66));
        botoLogin.setText("ACCEDIR");
        botoLogin.setActionCommand("");
        botoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoLoginActionPerformed(evt);
            }
        });

        isAdminRadioButton.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        isAdminRadioButton.setForeground(new java.awt.Color(141, 153, 174));
        isAdminRadioButton.setText("Sóc admin!");

        botoRegistrarse.setBackground(new java.awt.Color(255, 153, 153));
        botoRegistrarse.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        botoRegistrarse.setForeground(new java.awt.Color(43, 45, 66));
        botoRegistrarse.setText("REGISTRAR-SE");
        botoRegistrarse.setActionCommand("");
        botoRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(iconoUsuari)
                            .addComponent(iconoPassword))
                        .addGap(18, 18, 18)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usuariText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isAdminRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(iconoUsuari2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(botoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botoRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(iconoUsuari2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconoPassword)))
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(iconoUsuari)
                        .addComponent(usuariText, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(isAdminRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botoRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout PantallaLayout = new javax.swing.GroupLayout(Pantalla);
        Pantalla.setLayout(PantallaLayout);
        PantallaLayout.setHorizontalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PantallaLayout.setVerticalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pantalla.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Mètode que s'executa quan es fa clic al botó de login
     * @param evt
     * @throws IOException
     */
    private void loginActionPerformed(ActionEvent evt) throws IOException {
        String username = usuariText.getText();
        char[] password = passwordText.getPassword();
        Boolean isAdmin = isAdminRadioButton.isSelected();

        // Crida al mètode de login del controlador d'usuari
        userC.loginUser(username, password, isAdmin);
    }
    
    private void botoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoLoginActionPerformed
        // Acció associada al botó de login
        try {
            loginActionPerformed(evt);
        } catch (IOException ex) {
            Logger.getLogger(LoginV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botoLoginActionPerformed

    private void passwordTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFocusLost

    }//GEN-LAST:event_passwordTextFocusLost

    private void passwordTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFocusGained
        passwordText.setText("");
    }//GEN-LAST:event_passwordTextFocusGained

    private void usuariTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariTextFocusLost
        if (usuariText.getText().isEmpty()) {
            usuariText.setText("NOM D'USUARI");
        }
    }//GEN-LAST:event_usuariTextFocusLost

    private void usuariTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariTextFocusGained
        usuariText.setText("");
    }//GEN-LAST:event_usuariTextFocusGained

    private void botoRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoRegistrarseActionPerformed
        RegistreV pantallaRegistre = new RegistreV();
        pantallaRegistre.setLocationRelativeTo(null);
        pantallaRegistre.setVisible(true);
    }//GEN-LAST:event_botoRegistrarseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pantalla;
    private javax.swing.JButton botoLogin;
    private javax.swing.JButton botoRegistrarse;
    private javax.swing.JLabel iconoPassword;
    private javax.swing.JLabel iconoUsuari;
    private javax.swing.JLabel iconoUsuari2;
    private javax.swing.JRadioButton isAdminRadioButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField usuariText;
    // End of variables declaration//GEN-END:variables
}
