package vista;

import controlador.TreballadorC;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TreballadorM;
import util.GestorErrors;
import javax.xml.bind.DatatypeConverter;

/**
 * Classe que representa la pantalla de registre d'un nou usuari
 *
 * @author Enric
 */
public class RegistreV extends javax.swing.JFrame {

    /**
     * Instància de TreballadorC
     */
    TreballadorC treballadorC = new TreballadorC();

    /**
     * Constructor
     */
    public RegistreV() {
        initComponents();
    }

    /**
     * Mètode per buidar el formulari
     */
    private void buidarFormulari() {
        usuariRText.setText("DNI");
        mailRText.setText("CORREU ELECTRÒNIC");
        telefonRText.setText("TELÉFON");
        dataRText.setText("DATA NAIXEMENT YYYY-MM-DD");
        cognomsRText.setText("COGNOMS");
        nomRText.setText("NOM");
        passwordRText.setText("Password");
        password2RText.setText("Password");
    }

    /**
     * Mètode per validar el format de la data
     * @param date
     * @return
     */
    private boolean isValidDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Mètode per xifrar la contrasenya amb SHA-256
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hashedBytes);
    }

    /**
     * Mètode per registrar un nou usuari
     */
    public void registrarUsuari() {
        try {
            String dni = usuariRText.getText();
            String nom = nomRText.getText();
            String cognoms = cognomsRText.getText();
            String dataNaixement = dataRText.getText();
            String telefon = telefonRText.getText();
            String mail = mailRText.getText();
            String password = String.valueOf(passwordRText.getPassword());
            String password2 = String.valueOf(password2RText.getPassword());

            String user = nom + cognoms.substring(0, 1);

            if ("DNI".equals(dni) || "NOM".equals(nom) || "COGNOMS".equals(cognoms)
                    || "TELÉFON".equals(telefon) || "CORREU ELECTRÒNIC".equals(mail)
                    || "Password".equals(password) || "Password".equals(password2)
                    || "DATA NAIXEMENT YYYY-MM-DD".equals(dataNaixement)
                    || !isValidDateFormat(dataNaixement)) {
                // Gestionar l'error quan algun camp està buit o té el valor per defecte
                GestorErrors.displayError("Cal omplir tots els camps correctament abans d'afegir un treballador.");
            } else if (!password.equals(password2)) {
                // Gestionar l'error quan les contrasenyes no coincideixen
                GestorErrors.displayError("Les contrasenyes han de coincidir.");
            } else {
                // Parse dataNaixement a un objecte Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(dataNaixement);

                // Crea un objecte TreballadorM amb les dades del formulari
                TreballadorM nouTreballador = new TreballadorM();
                nouTreballador.setUser(user);
                nouTreballador.setUsername(user);
                nouTreballador.setNom(nom);
                nouTreballador.setCognoms(cognoms);
                nouTreballador.setDataNaixement(parsedDate);
                nouTreballador.setDni(dni);
                nouTreballador.setTelefon(telefon);
                nouTreballador.setEmail(mail);

                // Enviem text pla al servidor, que xifra la contrasenya. Si volguessim xifrar-la fariem servir hashPassword(password)
                nouTreballador.setPassword(password);
                nouTreballador.setPassword2(password2);

                // Crida al mètode afegeixTreballador a TreballadorC
                treballadorC.afegeixTreballador(nouTreballador);

                GestorErrors.mostraMissatge("Treballador amb nom d'usuari " + user + " afegit correctament.");
                
                dispose();
            }
        } catch (NumberFormatException e) {
            // Gestiona l'error en cas de que no sigui un valor numèric
            GestorErrors.displayError("Entrada no vàlida per a valors numèrics");
        } catch (ParseException e) {
            // Gestiona l'error en cas de que el format de la data sigui incorrecte
            GestorErrors.displayError("Format de data incorrecte. Utilitza el format YYYY-MM-DD.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pantalla = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();
        botoBuidarFormulari = new javax.swing.JButton();
        botoRegistrarse1 = new javax.swing.JButton();
        botoRetrocedir = new javax.swing.JToggleButton();
        dataRText = new javax.swing.JTextField();
        iconoData = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(43, 45, 66));

        Pantalla.setBackground(new java.awt.Color(43, 45, 66));

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

        iconoUsuari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/usuari.png"))); // NOI18N

        iconoNom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/nom.png"))); // NOI18N

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

        iconoCognoms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/cognoms.png"))); // NOI18N

        iconoTelefon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/telefon.png"))); // NOI18N

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

        iconoMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/mail.png"))); // NOI18N

        iconoPassword1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/password.png"))); // NOI18N

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

        iconoPassword2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/password.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(141, 153, 174));
        jLabel1.setText("DADES USUARI");

        botoBuidarFormulari.setBackground(new java.awt.Color(255, 153, 153));
        botoBuidarFormulari.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        botoBuidarFormulari.setForeground(new java.awt.Color(43, 45, 66));
        botoBuidarFormulari.setText("BUIDAR");
        botoBuidarFormulari.setActionCommand("");
        botoBuidarFormulari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoBuidarFormulariActionPerformed(evt);
            }
        });

        botoRegistrarse1.setBackground(new java.awt.Color(153, 255, 153));
        botoRegistrarse1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        botoRegistrarse1.setForeground(new java.awt.Color(43, 45, 66));
        botoRegistrarse1.setText("REGISTRAR-SE");
        botoRegistrarse1.setActionCommand("");
        botoRegistrarse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoRegistrarse1ActionPerformed(evt);
            }
        });

        botoRetrocedir.setBackground(new java.awt.Color(43, 45, 66));
        botoRetrocedir.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        botoRetrocedir.setForeground(new java.awt.Color(141, 153, 174));
        botoRetrocedir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/retrocedir.png"))); // NOI18N
        botoRetrocedir.setContentAreaFilled(false);
        botoRetrocedir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoRetrocedir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        botoRetrocedir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoRetrocedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoRetrocedirActionPerformed(evt);
            }
        });

        dataRText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        dataRText.setForeground(new java.awt.Color(141, 153, 174));
        dataRText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dataRText.setText("DATA NAIXEMENT YYYY-MM-DD");
        dataRText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dataRTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dataRTextFocusLost(evt);
            }
        });

        iconoData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/data.png"))); // NOI18N

        javax.swing.GroupLayout registrePanelLayout = new javax.swing.GroupLayout(registrePanel);
        registrePanel.setLayout(registrePanelLayout);
        registrePanelLayout.setHorizontalGroup(
            registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrePanelLayout.createSequentialGroup()
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(registrePanelLayout.createSequentialGroup()
                            .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(iconoPassword1)
                                .addComponent(iconoPassword2)
                                .addComponent(iconoMail)
                                .addComponent(iconoTelefon))
                            .addGap(18, 18, 18)
                            .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(telefonRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mailRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(password2RText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(registrePanelLayout.createSequentialGroup()
                            .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(registrePanelLayout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(iconoCognoms)
                                        .addComponent(iconoNom)
                                        .addComponent(iconoUsuari1)
                                        .addComponent(iconoData)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registrePanelLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(botoRetrocedir)))
                            .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(registrePanelLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nomRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cognomsRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usuariRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(registrePanelLayout.createSequentialGroup()
                                    .addGap(74, 74, 74)
                                    .addComponent(jLabel1))))
                        .addComponent(dataRText, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registrePanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(botoBuidarFormulari, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botoRegistrarse1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        registrePanelLayout.setVerticalGroup(
            registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrePanelLayout.createSequentialGroup()
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrePanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(botoRetrocedir))
                    .addGroup(registrePanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)))
                .addGap(40, 40, 40)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoUsuari1)
                    .addComponent(usuariRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoNom)
                    .addComponent(nomRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoCognoms)
                    .addComponent(cognomsRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconoData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoTelefon)
                    .addComponent(telefonRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoMail)
                    .addComponent(mailRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoPassword1)
                    .addComponent(passwordRText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(iconoPassword2)
                    .addComponent(password2RText, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(registrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botoBuidarFormulari, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botoRegistrarse1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout PantallaLayout = new javax.swing.GroupLayout(Pantalla);
        Pantalla.setLayout(PantallaLayout);
        PantallaLayout.setHorizontalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PantallaLayout.createSequentialGroup()
                .addComponent(registrePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PantallaLayout.setVerticalGroup(
            PantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registrePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void password2RTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password2RTextFocusLost
        if (passwordRText.getText().isEmpty()) {
            passwordRText.setText("Password");
        }
    }//GEN-LAST:event_password2RTextFocusLost

    private void password2RTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password2RTextFocusGained
        password2RText.setText("");
    }//GEN-LAST:event_password2RTextFocusGained

    private void passwordRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordRTextFocusLost
        if (password2RText.getText().isEmpty()) {
            password2RText.setText("Password");
        }
    }//GEN-LAST:event_passwordRTextFocusLost

    private void passwordRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordRTextFocusGained
        passwordRText.setText("");
    }//GEN-LAST:event_passwordRTextFocusGained

    private void mailRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailRTextFocusLost
        if (mailRText.getText().isEmpty()) {
            mailRText.setText("CORREU ELECTRÒNIC");
        }
    }//GEN-LAST:event_mailRTextFocusLost

    private void mailRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailRTextFocusGained
        mailRText.setText("");
    }//GEN-LAST:event_mailRTextFocusGained

    private void telefonRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonRTextFocusLost
        if (telefonRText.getText().isEmpty()) {
            telefonRText.setText("TELÉFON");
        }
    }//GEN-LAST:event_telefonRTextFocusLost

    private void telefonRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonRTextFocusGained
        telefonRText.setText("");
    }//GEN-LAST:event_telefonRTextFocusGained

    private void cognomsRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cognomsRTextFocusLost
        if (cognomsRText.getText().isEmpty()) {
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
        if (nomRText.getText().isEmpty()) {
            nomRText.setText("NOM");
        }
    }//GEN-LAST:event_nomRTextFocusLost

    private void nomRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomRTextFocusGained
        nomRText.setText("");
    }//GEN-LAST:event_nomRTextFocusGained

    private void usuariRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariRTextFocusLost
        if (usuariRText.getText().isEmpty()) {
            usuariRText.setText("DNI");
        }
    }//GEN-LAST:event_usuariRTextFocusLost

    private void usuariRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuariRTextFocusGained
        usuariRText.setText("");
    }//GEN-LAST:event_usuariRTextFocusGained

    private void botoBuidarFormulariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBuidarFormulariActionPerformed
        buidarFormulari();
    }//GEN-LAST:event_botoBuidarFormulariActionPerformed

    private void botoRegistrarse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoRegistrarse1ActionPerformed
        registrarUsuari();
    }//GEN-LAST:event_botoRegistrarse1ActionPerformed

    private void botoRetrocedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoRetrocedirActionPerformed
        dispose();
    }//GEN-LAST:event_botoRetrocedirActionPerformed

    private void dataRTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dataRTextFocusGained
        dataRText.setText("");
    }//GEN-LAST:event_dataRTextFocusGained

    private void dataRTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dataRTextFocusLost
        if (usuariRText.getText().isEmpty()) {
            usuariRText.setText("DATA NAIXEMENT YYYY-MM-DD");
        }
    }//GEN-LAST:event_dataRTextFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pantalla;
    private javax.swing.JButton botoBuidarFormulari;
    private javax.swing.JButton botoRegistrarse1;
    private javax.swing.JToggleButton botoRetrocedir;
    private javax.swing.JTextField cognomsRText;
    private javax.swing.JTextField dataRText;
    private javax.swing.JLabel iconoCognoms;
    private javax.swing.JLabel iconoData;
    private javax.swing.JLabel iconoMail;
    private javax.swing.JLabel iconoNom;
    private javax.swing.JLabel iconoPassword1;
    private javax.swing.JLabel iconoPassword2;
    private javax.swing.JLabel iconoTelefon;
    private javax.swing.JLabel iconoUsuari1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField mailRText;
    private javax.swing.JTextField nomRText;
    private javax.swing.JPasswordField password2RText;
    private javax.swing.JPasswordField passwordRText;
    private javax.swing.JPanel registrePanel;
    private javax.swing.JTextField telefonRText;
    private javax.swing.JTextField usuariRText;
    // End of variables declaration//GEN-END:variables
}
