package vista;

import controlador.ClientC;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ClientM;

/**
 * Classe per representar la pantalla de clients
 *
 * @author Enric
 */
public class ClientV extends javax.swing.JPanel {

    private ClientC clientC = new ClientC();
    private List<ClientM> clientList;
    private String missatgeBuscador = "Busca tots els clients o busca per DNI";

    /**
     * Constructor ClientsV
     */
    public ClientV() {
        initComponents();
    }

    /**
     * Mètode per actualitzar la taula amb tots els clients
     */
    public void actualitzaTaulaAmbClients() {
        DefaultTableModel tableModel = (DefaultTableModel) taulaClients.getModel();
        tableModel.setRowCount(0); // Esborra les dades existents.

        // Crida al mètode getClients de ClientC per obtenir les dades més recents dels clients.
        ClientM clients = clientC.getClients();

        if (clients != null) {
            // Emmagatzema els objectes Client a la llista
            clientList = Arrays.asList(clients.getClients());

            // Omple la taula amb les dades dels clients.
            for (ClientM client : clients.getClients()) {
                Object[] rowData = {
                    client.getId(),
                    client.getDni(),
                    client.getNom(),
                    client.getCognoms(),
                    client.getTelefon(),
                    client.getEmail()
                };
                tableModel.addRow(rowData);
            }

        } else {
            // Gestionar el cas en què falli la recuperació de les dades del client.
            JOptionPane.showMessageDialog(this, "Error en recuperar les dades del client.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode per actualitzar la taula amb un client específic
     *
     * @param dni El DNI del client
     */
    public void actualitzaTaulaAmbClientPerDNI(String dni) {
        ClientM client = clientC.getClientPerDNI(dni);

        DefaultTableModel tableModel = (DefaultTableModel) taulaClients.getModel();
        tableModel.setRowCount(0); // Esborra les dades existents.

        if (client != null) {
            // Actualitza la clientList amb l'únic client de la cerca
            clientList = Collections.singletonList(client);

            // Omple la taula amb les dades del client.
            Object[] rowData = {
                client.getId(),
                client.getDni(),
                client.getNom(),
                client.getCognoms(),
                client.getTelefon(),
                client.getEmail()
            };
            tableModel.addRow(rowData);

        } else {
            // Gestionar el cas en què falli la recuperació de les dades del client.
            JOptionPane.showMessageDialog(this, "Error en recuperar les dades del client.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode per buidar la taula
     */
    public void buidaTaula() {
        DefaultTableModel modelDeTaula = (DefaultTableModel) taulaClients.getModel();
        modelDeTaula.setRowCount(0);
    }

    /**
     * Mètode per buidar formulari
     */
    public void buidaFormulari() {
        taulaClients.clearSelection();

        dniText.setText("");
        nomText.setText("");
        cognomsText.setText("");
        telefonText.setText("");
        emailText.setText("");
        dataNaixementText.setText("");
        bioText.setText("");
        puntsText.setText("");
    }

    /**
     * Mètode per omplir el formulari amb les dades del client seleccionat
     */
    public void ompleFormulariAmbDadesClient() {
        // Selecciona el client
        int fila = taulaClients.getSelectedRow();

        if (fila != -1 && clientList != null && fila < clientList.size()) {
            ClientM selectedClient = clientList.get(fila);

            // Omple el formulari amb les dades del client seleccionat
            dniText.setText(selectedClient.getDni());
            nomText.setText(selectedClient.getNom());
            cognomsText.setText(selectedClient.getCognoms());
            telefonText.setText(selectedClient.getTelefon());
            emailText.setText(selectedClient.getEmail());
            dataNaixementText.setText(selectedClient.getDataNaixement());
            bioText.setText(selectedClient.getBio());
            puntsText.setText(selectedClient.getPunts());
        }
    }

    /**
     * Mètode per determinar si omplim la taula amb tots els clients o amb un
     * client segons el text a buscadorClients
     */
    public void buscaClients() {
        // Obtenir el text del camp de text BuscadorClient
        String dniClient = buscadorClients.getText();

        // Comprovar si el text no és buit 
        if (!dniClient.isEmpty() && !dniClient.equals(missatgeBuscador)) {
            try {
                // Crida al mètode per mostrar el client individual a la taula
                actualitzaTaulaAmbClientPerDNI(dniClient);
            } catch (NumberFormatException e) {
                // Gestionar el cas en què dniClient no sigui una entrada vàlida
                System.out.println("Format de dniClient no vàlid. Mostrant tots els clients en lloc d'això.");

                // Mostra tots els clients
                actualitzaTaulaAmbClients();
            }
        } else {
            // Si el text és buit, obtenir tots els clients i omplir la taula
            actualitzaTaulaAmbClients();
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

        buscadorPanel = new javax.swing.JPanel();
        buscadorClients = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taulaClients = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        botoBuscaClient = new javax.swing.JButton();
        botoNetejaBuscadorClient = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        dniLabel = new javax.swing.JLabel();
        nomLabel = new javax.swing.JLabel();
        cognomsLabel = new javax.swing.JLabel();
        telefonLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        dataNaixementLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dniText = new javax.swing.JTextField();
        separadorLabel = new javax.swing.JLabel();
        nomText = new javax.swing.JTextField();
        cognomsText = new javax.swing.JTextField();
        telefonText = new javax.swing.JTextField();
        emailText = new javax.swing.JTextField();
        dataNaixementText = new javax.swing.JTextField();
        bioText = new javax.swing.JTextField();
        bioLabel = new javax.swing.JLabel();
        puntsText = new javax.swing.JTextField();
        puntsLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(217, 4, 41));

        buscadorPanel.setBackground(new java.awt.Color(217, 4, 41));

        buscadorClients.setBackground(new java.awt.Color(237, 242, 244));
        buscadorClients.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        buscadorClients.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        buscadorClients.setText("Busca tots els clients o busca per DNI");
        buscadorClients.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                buscadorClientsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                buscadorClientsFocusLost(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N

        taulaClients.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        taulaClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nom", "Cognoms", "Telèfon", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taulaClients.getTableHeader().setReorderingAllowed(false);
        taulaClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaClientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taulaClients);
        taulaClients.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (taulaClients.getColumnModel().getColumnCount() > 0) {
            taulaClients.getColumnModel().getColumn(0).setResizable(false);
            taulaClients.getColumnModel().getColumn(0).setPreferredWidth(20);
            taulaClients.getColumnModel().getColumn(1).setResizable(false);
            taulaClients.getColumnModel().getColumn(1).setPreferredWidth(100);
            taulaClients.getColumnModel().getColumn(2).setResizable(false);
            taulaClients.getColumnModel().getColumn(2).setPreferredWidth(90);
            taulaClients.getColumnModel().getColumn(3).setResizable(false);
            taulaClients.getColumnModel().getColumn(3).setPreferredWidth(180);
            taulaClients.getColumnModel().getColumn(4).setResizable(false);
            taulaClients.getColumnModel().getColumn(4).setPreferredWidth(100);
            taulaClients.getColumnModel().getColumn(5).setResizable(false);
            taulaClients.getColumnModel().getColumn(5).setPreferredWidth(300);
        }
        taulaClients.setRowHeight(40);

        jPanel6.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        botoBuscaClient.setBackground(new java.awt.Color(43, 45, 66));
        botoBuscaClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/lupaB.png"))); // NOI18N
        botoBuscaClient.setBorder(null);
        botoBuscaClient.setBorderPainted(false);
        botoBuscaClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoBuscaClientActionPerformed(evt);
            }
        });

        botoNetejaBuscadorClient.setBackground(new java.awt.Color(43, 45, 66));
        botoNetejaBuscadorClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/XB.png"))); // NOI18N
        botoNetejaBuscadorClient.setBorder(null);
        botoNetejaBuscadorClient.setBorderPainted(false);
        botoNetejaBuscadorClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoNetejaBuscadorClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buscadorPanelLayout = new javax.swing.GroupLayout(buscadorPanel);
        buscadorPanel.setLayout(buscadorPanelLayout);
        buscadorPanelLayout.setHorizontalGroup(
            buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscadorPanelLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                        .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(buscadorPanelLayout.createSequentialGroup()
                                .addComponent(botoBuscaClient, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscadorClients)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botoNetejaBuscadorClient, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buscadorPanelLayout.setVerticalGroup(
            buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscadorPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buscadorClients, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(botoBuscaClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botoNetejaBuscadorClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );

        jPanel2.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        infoPanel.setBackground(new java.awt.Color(217, 4, 41));

        jPanel8.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        dniLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        dniLabel.setForeground(new java.awt.Color(237, 242, 244));
        dniLabel.setText("DNI");

        nomLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        nomLabel.setForeground(new java.awt.Color(237, 242, 244));
        nomLabel.setText("Nom");

        cognomsLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        cognomsLabel.setForeground(new java.awt.Color(237, 242, 244));
        cognomsLabel.setText("Cognoms");

        telefonLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        telefonLabel.setForeground(new java.awt.Color(237, 242, 244));
        telefonLabel.setText("Telèfon");

        emailLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(237, 242, 244));
        emailLabel.setText("E-mail");

        dataNaixementLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        dataNaixementLabel.setForeground(new java.awt.Color(237, 242, 244));
        dataNaixementLabel.setText("Data");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(237, 242, 244));
        jLabel14.setText("INFORMACIÓ DEL CLIENT");

        dniText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        dniText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        separadorLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        separadorLabel.setForeground(new java.awt.Color(237, 242, 244));
        separadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        separadorLabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");

        nomText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        nomText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        cognomsText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        cognomsText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        telefonText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        telefonText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        emailText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        emailText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        dataNaixementText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        dataNaixementText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        bioText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        bioText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        bioLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        bioLabel.setForeground(new java.awt.Color(237, 242, 244));
        bioLabel.setText("Bio");

        puntsText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        puntsText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        puntsLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        puntsLabel.setForeground(new java.awt.Color(237, 242, 244));
        puntsLabel.setText("Punts");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dniLabel)
                                    .addComponent(nomLabel)
                                    .addComponent(cognomsLabel)
                                    .addComponent(telefonLabel)
                                    .addComponent(emailLabel))
                                .addGap(38, 38, 38)
                                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailText)
                                    .addComponent(telefonText)
                                    .addComponent(cognomsText)
                                    .addComponent(nomText)
                                    .addComponent(dniText)))
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(puntsLabel)
                                .addGap(81, 81, 81)
                                .addComponent(puntsText))
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(infoPanelLayout.createSequentialGroup()
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dataNaixementLabel)
                                            .addComponent(bioLabel))
                                        .addGap(92, 92, 92)
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bioText, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                            .addComponent(dataNaixementText))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel14))
                            .addComponent(separadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorLabel)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dniText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dniLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cognomsText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cognomsLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefonLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataNaixementLabel)
                            .addComponent(dataNaixementText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bioLabel)
                            .addComponent(bioText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(puntsLabel)
                            .addComponent(puntsText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscadorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscadorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void taulaClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaClientsMouseClicked
        ompleFormulariAmbDadesClient();
    }//GEN-LAST:event_taulaClientsMouseClicked

    private void botoBuscaClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBuscaClientActionPerformed
        buscaClients();
    }//GEN-LAST:event_botoBuscaClientActionPerformed

    private void botoNetejaBuscadorClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoNetejaBuscadorClientActionPerformed
        buscadorClients.setText(missatgeBuscador);
        buidaFormulari();
        buidaTaula();
    }//GEN-LAST:event_botoNetejaBuscadorClientActionPerformed

    private void buscadorClientsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorClientsFocusGained
        buscadorClients.setText("");
    }//GEN-LAST:event_buscadorClientsFocusGained

    private void buscadorClientsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorClientsFocusLost
        if (buscadorClients.getText().isEmpty()) {
            buscadorClients.setText(missatgeBuscador);
        }
    }//GEN-LAST:event_buscadorClientsFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bioLabel;
    private javax.swing.JTextField bioText;
    private javax.swing.JButton botoBuscaClient;
    private javax.swing.JButton botoNetejaBuscadorClient;
    private javax.swing.JTextField buscadorClients;
    private javax.swing.JPanel buscadorPanel;
    private javax.swing.JLabel cognomsLabel;
    private javax.swing.JTextField cognomsText;
    private javax.swing.JLabel dataNaixementLabel;
    private javax.swing.JTextField dataNaixementText;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniText;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailText;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JTextField nomText;
    private javax.swing.JLabel puntsLabel;
    private javax.swing.JTextField puntsText;
    private javax.swing.JLabel separadorLabel;
    private javax.swing.JTable taulaClients;
    private javax.swing.JLabel telefonLabel;
    private javax.swing.JTextField telefonText;
    // End of variables declaration//GEN-END:variables
}
