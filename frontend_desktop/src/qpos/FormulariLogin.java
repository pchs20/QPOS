package qpos;

/**
 *
 * @author Enric
 */
public class FormulariLogin extends javax.swing.JFrame {

    public FormulariLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pantalla = new javax.swing.JPanel();
        separador = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        iconoUsuari2 = new javax.swing.JLabel();
        usuariText = new javax.swing.JTextField();
        iconoUsuari = new javax.swing.JLabel();
        iconoPassword = new javax.swing.JLabel();
        passwordText = new javax.swing.JPasswordField();
        botoLogin = new javax.swing.JButton();
        registrePanel = new javax.swing.JPanel();
        usuariRText = new javax.swing.JTextField();
        iconoUsuari1 = new javax.swing.JLabel();
        iconoNom = new javax.swing.JLabel();
        nomRText = new javax.swing.JTextField();
        cognomsRText = new javax.swing.JTextField();
        iconoCognoms = new javax.swing.JLabel();
        iconoTelefon = new javax.swing.JLabel();
        telefonRText = new javax.swing.JTextField();
        mailRText = new javax.swing.JTextField();
        iconoMail = new javax.swing.JLabel();
        iconoPassword1 = new javax.swing.JLabel();
        passwordRText = new javax.swing.JPasswordField();
        password2RText = new javax.swing.JPasswordField();
        iconoPassword2 = new javax.swing.JLabel();
        botoLogin1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(43, 45, 66));

        Pantalla.setBackground(new java.awt.Color(43, 45, 66));

        separador.setBackground(new java.awt.Color(141, 153, 174));

        javax.swing.GroupLayout separadorLayout = new javax.swing.GroupLayout(separador);
        separador.setLayout(separadorLayout);
        separadorLayout.setHorizontalGroup(
            separadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        separadorLayout.setVerticalGroup(
            separadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );

        loginPanel.setBackground(new java.awt.Color(43, 45, 66));

        iconoUsuari2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoUsuari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/punt_de_venda.png"))); // NOI18N

        usuariText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        usuariText.setForeground(new java.awt.Color(141, 153, 174));
        usuariText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuariText.setText("DNI");
        usuariText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuariTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuariTextFocusLost(evt);
            }
        });

        iconoUsuari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/usuari.png"))); // NOI18N

        iconoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/password.png"))); // NOI18N

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

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(iconoUsuari2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(iconoUsuari)
                            .addComponent(iconoPassword))
                        .addGap(18, 18, 18)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usuariText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(botoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(iconoUsuari2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconoPassword)))
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(iconoUsuari)
                        .addComponent(usuariText, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(150, 150, 150)
                .addComponent(botoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        registrePanel.setBackground(new java.awt.Color(43, 45, 66));

        usuariRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        usuariRText.setForeground(new java.awt.Color(141, 153, 174));
        usuariRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuariRText.setText("DNI");
        usuariRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuariRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuariRTextFocusLost(evt);
            }
        });

        iconoUsuari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/usuari.png"))); // NOI18N

        iconoNom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/nom.png"))); // NOI18N

        nomRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        nomRText.setForeground(new java.awt.Color(141, 153, 174));
        nomRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nomRText.setText("NOM");
        nomRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nomRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nomRTextFocusLost(evt);
            }
        });
        nomRText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomRTextActionPerformed(evt);
            }
        });

        cognomsRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        cognomsRText.setForeground(new java.awt.Color(141, 153, 174));
        cognomsRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cognomsRText.setText("COGNOMS");
        cognomsRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cognomsRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cognomsRTextFocusLost(evt);
            }
        });

        iconoCognoms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/cognoms.png"))); // NOI18N

        iconoTelefon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/telefon.png"))); // NOI18N

        telefonRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        telefonRText.setForeground(new java.awt.Color(141, 153, 174));
        telefonRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        telefonRText.setText("TELÈFON");
        telefonRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonRTextFocusLost(evt);
            }
        });

        mailRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        mailRText.setForeground(new java.awt.Color(141, 153, 174));
        mailRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mailRText.setText("CORREU ELECTRÒNIC");
        mailRText.setToolTipText("");
        mailRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mailRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailRTextFocusLost(evt);
            }
        });

        iconoMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/mail.png"))); // NOI18N

        iconoPassword1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/password.png"))); // NOI18N

        passwordRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        passwordRText.setForeground(new java.awt.Color(141, 157, 174));
        passwordRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordRText.setText("Password");
        passwordRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordRTextFocusLost(evt);
            }
        });

        password2RText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        password2RText.setForeground(new java.awt.Color(141, 157, 174));
        password2RText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password2RText.setText("Password");
        password2RText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password2RTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password2RTextFocusLost(evt);
            }
        });

        iconoPassword2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/password.png"))); // NOI18N

        botoLogin1.setBackground(new java.awt.Color(141, 153, 174));
        botoLogin1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        botoLogin1.setForeground(new java.awt.Color(43, 45, 66));
        botoLogin1.setText("REGISTRAR-SE");
        botoLogin1.setActionCommand("");
        botoLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoLogin1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registrePanelLayout = new javax.swing.GroupLayout(registrePanel);
        registrePanel.setLayout(registrePanelLayout);
        registrePanelLayout.setHorizontalGroup(
            registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoPassword1)
                    .addComponent(iconoPassword2)
                    .addComponent(iconoMail)
                    .addComponent(iconoTelefon)
                    .addComponent(iconoCognoms)
                    .addComponent(iconoNom)
                    .addComponent(iconoUsuari1))
                .addGap(18, 18, 18)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cognomsRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mailRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password2RText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuariRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registrePanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(botoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        registrePanelLayout.setVerticalGroup(
            registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoUsuari1)
                    .addComponent(usuariRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoNom)
                    .addComponent(nomRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoCognoms)
                    .addComponent(cognomsRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoTelefon)
                    .addComponent(telefonRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoMail)
                    .addComponent(mailRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoPassword1)
                    .addComponent(passwordRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoPassword2)
                    .addComponent(password2RText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(botoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout PantallaLayout = new javax.swing.GroupLayout(Pantalla);
        Pantalla.setLayout(PantallaLayout);
        PantallaLayout.setHorizontalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(registrePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        PantallaLayout.setVerticalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PantallaLayout.createSequentialGroup()
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PantallaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(registrePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(PantallaLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void botoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoLoginActionPerformed
        ConnexioLogin log = new ConnexioLogin();
        log.validaUsuari(usuariText, passwordText);
    }//GEN-LAST:event_botoLoginActionPerformed

    private void passwordTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFocusLost

    }//GEN-LAST:event_passwordTextFocusLost

    private void passwordTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTextFocusGained
        passwordText.setText("");
    }//GEN-LAST:event_passwordTextFocusGained

    private void usuariTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariTextFocusLost
        if (usuariText.getText().isEmpty()){
            usuariText.setText("DNI");
        }
    }//GEN-LAST:event_usuariTextFocusLost

    private void usuariTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariTextFocusGained
        usuariText.setText("");
    }//GEN-LAST:event_usuariTextFocusGained

    private void botoLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoLogin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botoLogin1ActionPerformed

    private void password2RTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password2RTextFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_password2RTextFocusLost

    private void password2RTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password2RTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_password2RTextFocusGained

    private void passwordRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordRTextFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordRTextFocusLost

    private void passwordRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordRTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordRTextFocusGained

    private void mailRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailRTextFocusLost
        if (mailRText.getText().isEmpty()){
            mailRText.setText("CORREU ELECTRÒNIC");
        }
    }//GEN-LAST:event_mailRTextFocusLost

    private void mailRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailRTextFocusGained
        mailRText.setText("");
    }//GEN-LAST:event_mailRTextFocusGained

    private void telefonRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonRTextFocusLost
        if (telefonRText.getText().isEmpty()){
            telefonRText.setText("TELÉFON");
        }
    }//GEN-LAST:event_telefonRTextFocusLost

    private void telefonRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonRTextFocusGained
        telefonRText.setText("");
    }//GEN-LAST:event_telefonRTextFocusGained

    private void cognomsRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cognomsRTextFocusLost
        if (cognomsRText.getText().isEmpty()){
            cognomsRText.setText("COGNOMS");
        }
    }//GEN-LAST:event_cognomsRTextFocusLost

    private void cognomsRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cognomsRTextFocusGained
        cognomsRText.setText("");
    }//GEN-LAST:event_cognomsRTextFocusGained

    private void nomRTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomRTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomRTextActionPerformed

    private void nomRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomRTextFocusLost
        if (nomRText.getText().isEmpty()){
            nomRText.setText("NOM");
        }
    }//GEN-LAST:event_nomRTextFocusLost

    private void nomRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomRTextFocusGained
        nomRText.setText("");
    }//GEN-LAST:event_nomRTextFocusGained

    private void usuariRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariRTextFocusLost
        if (usuariRText.getText().isEmpty()){
            usuariRText.setText("DNI");
        }
    }//GEN-LAST:event_usuariRTextFocusLost

    private void usuariRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariRTextFocusGained
        usuariRText.setText("");
    }//GEN-LAST:event_usuariRTextFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pantalla;
    private javax.swing.JButton botoLogin;
    private javax.swing.JButton botoLogin1;
    private javax.swing.JTextField cognomsRText;
    private javax.swing.JLabel iconoCognoms;
    private javax.swing.JLabel iconoMail;
    private javax.swing.JLabel iconoNom;
    private javax.swing.JLabel iconoPassword;
    private javax.swing.JLabel iconoPassword1;
    private javax.swing.JLabel iconoPassword2;
    private javax.swing.JLabel iconoTelefon;
    private javax.swing.JLabel iconoUsuari;
    private javax.swing.JLabel iconoUsuari1;
    private javax.swing.JLabel iconoUsuari2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField mailRText;
    private javax.swing.JTextField nomRText;
    private javax.swing.JPasswordField password2RText;
    private javax.swing.JPasswordField passwordRText;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JPanel registrePanel;
    private javax.swing.JPanel separador;
    private javax.swing.JTextField telefonRText;
    private javax.swing.JTextField usuariRText;
    private javax.swing.JTextField usuariText;
    // End of variables declaration//GEN-END:variables
}
