package vista;

import com.toedter.calendar.JDayChooser;
import controlador.EsdevenimentC;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import model.AssistenciaM;
import model.AuthorizationM;
import model.ClientM;
import model.EsdevenimentM;
import model.nouEsdevenimentM;
import util.DateUtils;
import util.GestorErrors;

/**
 * Classe que representa la pantalla de esdeveniments
 *
 * @author Enric
 */
public class EsdevenimentV extends javax.swing.JPanel {

    /**
     * Instància de EsdevenimentC
     */
    EsdevenimentC esdevenimentC = new EsdevenimentC();

    List<EsdevenimentM> esdeveniments;

    private String missatgeBuscador = "Busca tots els esdeveniments";

    /**
     * Constructor de Esdeveniments
     */
    public EsdevenimentV() {
        initComponents();

    }

    /**
     * Mètode per mostrar la llista d'esdeveniments a la taula
     *
     * @param esdeveniments
     */
    public void actualitzaTaulaAmbEsdeveniments(List<EsdevenimentM> esdeveniments) {
        DefaultTableModel tableModel = (DefaultTableModel) taulaEsdeveniments.getModel();
        tableModel.setRowCount(0); // Esborra dades existents.

        if (esdeveniments != null) {
            // Omple la taula amb les dades de l'esdeveniment
            for (EsdevenimentM esdeveniment : esdeveniments) {
                String formattedDate = DateUtils.format(esdeveniment.getData());

                Object[] rowData = {
                    esdeveniment.getId(),
                    esdeveniment.getNom(),
                    formattedDate,
                    esdeveniment.getAforament(),
                    esdeveniment.getDurada(),
                    esdeveniment.getUbicacio()
                };
                tableModel.addRow(rowData);
            }
        } else {
            // Gestiona l'error obtenint les dades de l'esdeveniment.
            JOptionPane.showMessageDialog(this, "Error retrieving esdeveniment data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode per omplir la taula amb els assistents de l'esdeveniment
     */
    public void actualitzaTaulaAssistents() {
        // Obté la fila seleccionada de la taulaEsdeveniments
        int filaSeleccionada = taulaEsdeveniments.getSelectedRow();

        // Verifica si hi ha una fila seleccionada
        if (filaSeleccionada != -1) {
            // Obté l'objecte EsdevenimentM de la fila seleccionada
            EsdevenimentM esdevenimentSeleccionat = esdeveniments.get(filaSeleccionada);

            // Obté la llista d'assistents per a l'esdeveniment seleccionat
            List<AssistenciaM> assistents = esdevenimentSeleccionat.getAssistencies();

            // Obté el model de la taulaAssistents
            DefaultTableModel taulaAssistentsModel = (DefaultTableModel) taulaAssistents.getModel();

            // Netega les dades existents de la taulaAssistents
            taulaAssistentsModel.setRowCount(0);

            // Omple la taulaAssistents amb les dades dels assistents
            for (AssistenciaM assistencia : assistents) {
                ClientM client = assistencia.getClient();
                Object[] rowData = {
                    client.getNom(),
                    client.getCognoms(),
                    client.getTelefon()
                };
                taulaAssistentsModel.addRow(rowData);
            }
        } else {
            // Si no hi ha cap fila seleccionada, neteja la taulaAssistents
            DefaultTableModel taulaAssistentsModel = (DefaultTableModel) taulaAssistents.getModel();
            taulaAssistentsModel.setRowCount(0);
        }
    }

    /**
     * Mètode per buidar la taula
     */
    public void buidaTaula() {
        DefaultTableModel modelDeTaula = (DefaultTableModel) taulaEsdeveniments.getModel();
        modelDeTaula.setRowCount(0);
        DefaultTableModel taulaAssistentsModel = (DefaultTableModel) taulaAssistents.getModel();
        taulaAssistentsModel.setRowCount(0);
    }

    /**
     * Mètode per buidar formulari
     */
    public void buidaFormulari() {
        taulaEsdeveniments.clearSelection();

        nomText.setText("");
        descripcioText.setText("");
        ubicacioText.setText("");
        aforamentText.setText("");
        duradaText.setText("");
        dataText.setText("");
    }

    /**
     * Mètode per omplir el formulari amb les dades de l'esdeveniment
     */
    public void ompleFormulariAmbDadesEsdeveniment() {
        // Selecciona l'esdeveniment de la taula
        int fila = taulaEsdeveniments.getSelectedRow();

        if (fila != -1 && esdeveniments != null && fila < esdeveniments.size()) {
            EsdevenimentM selectedEsdeveniment = esdeveniments.get(fila);

            // Omple el formulari amb les dades de l'esdeveniment seleccionat
            String nom = selectedEsdeveniment.getNom();
            String descripcio = selectedEsdeveniment.getDescripcio();
            String ubicacio = selectedEsdeveniment.getUbicacio();
            int aforament = selectedEsdeveniment.getAforament();
            String durada = selectedEsdeveniment.getDurada();
            String data = DateUtils.format(selectedEsdeveniment.getData());

            nomText.setText(nom);
            descripcioText.setText(descripcio);
            ubicacioText.setText(ubicacio);
            aforamentText.setText(String.valueOf(aforament));
            duradaText.setText(durada);
            dataText.setText(data.substring(0, 8));

            try {
                // Parse el string per obtenir un objecte Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date date = dateFormat.parse(data);

                // Posa la data a SpinnerDateModel
                SpinnerDateModel model = new SpinnerDateModel(date, null, null, java.util.Calendar.HOUR_OF_DAY);

                // Actualitza horaSpinner amb el nou model
                horaSpinner.setModel(model);

                // Parse la part HH:mm de la data i posa-ho a horaSpinner
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date time = timeFormat.parse(data.substring(9, 14));
                horaSpinner.setValue(time);

            } catch (ParseException ex) {
                Logger.getLogger(EsdevenimentV.class.getName()).log(Level.SEVERE, null, ex);
            }
            actualitzaTaulaAssistents();
        }
    }

    /**
     * Mètode per editar un esdeveniment
     */
    public void editarEsdeveniment() {
        try {
            // Obté l'índex de la fila seleccionada
            int filaSeleccionada = taulaEsdeveniments.getSelectedRow();

            // Comprova si s'ha seleccionat una fila
            if (filaSeleccionada != -1) {
                // Obté l'objecte EsdevenimentM de la llista
                EsdevenimentM selectedEsdeveniment = esdeveniments.get(filaSeleccionada);

                String nom = nomText.getText();
                String descripcio = descripcioText.getText();
                String ubicacio = ubicacioText.getText();
                int aforament = Integer.parseInt(aforamentText.getText());
                String durada = duradaText.getText();

                String timeFormat = "HH:mm:ss";
                String dateFormat = "dd/MM/yy HH:mm:ss";

                SimpleDateFormat sdfTime = new SimpleDateFormat(timeFormat);
                SimpleDateFormat sdfFinalDate = new SimpleDateFormat(dateFormat);

                String dia = dataText.getText();
                String hora = sdfTime.format(horaSpinner.getValue());
                String dataSenseFormat = dia + " " + hora;

                Date data = sdfFinalDate.parse(dataSenseFormat);  // Use parse instead of format

                // Comprova si hi ha algun camp buit
                if (nom.isEmpty() || descripcio.isEmpty() || ubicacio.isEmpty() || durada.isEmpty()) {
                    // Gestionar l'error quan algun camp està buit
                    GestorErrors.displayError("Cal omplir tots els camps abans d'editar un producte.");
                } else {

                    // Crea un objecte nouEsdevenimentM amb la informació actualitzada
                    nouEsdevenimentM esdevenimentEditat = new nouEsdevenimentM();
                    esdevenimentEditat.setNom(nom);
                    esdevenimentEditat.setDescripcio(descripcio);
                    esdevenimentEditat.setData(data);
                    esdevenimentEditat.setUbicacio(ubicacio);
                    esdevenimentEditat.setAforament(aforament);
                    esdevenimentEditat.setDurada(durada);
                    esdevenimentEditat.setCreador_id(AuthorizationM.getInstance().getId());

                    // Crida al mètode editarEsdeveniment a EsdevenimentC
                    esdevenimentC.editarEsdeveniment(selectedEsdeveniment.getId(), esdevenimentEditat);

                    // Actualitza la taula després de l'edició
                    esdeveniments = esdevenimentC.getEsdeveniments();
                    actualitzaTaulaAmbEsdeveniments(esdeveniments);

                }
            } else {
                // Si no s'ha seleccionat cap fila, mostra un missatge d'error
                GestorErrors.displayError("Cal seleccionar un esdeveniment per editar-lo.");
            }
        } catch (NumberFormatException e) {
            // Gestionar el cas en què el text no sigui un número vàlid
            GestorErrors.displayError("Entrada no vàlida per a valors numèrics");
        } catch (ParseException ex) {
            Logger.getLogger(EsdevenimentV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mètode per afegir un esdeveniment
     */
    public void afegirEsdeveniment() {
        try {
            String nom = nomText.getText();
            String descripcio = descripcioText.getText();
            String ubicacio = ubicacioText.getText();
            int aforament = Integer.parseInt(aforamentText.getText());
            String durada = duradaText.getText();

            String timeFormat = "HH:mm:ss";
            String dateFormat = "dd/MM/yy HH:mm:ss";

            SimpleDateFormat sdfTime = new SimpleDateFormat(timeFormat);
            SimpleDateFormat sdfFinalDate = new SimpleDateFormat(dateFormat);

            String dia = dataText.getText();
            String hora = sdfTime.format(horaSpinner.getValue());
            String dataSenseFormat = dia + " " + hora;

            Date data = sdfFinalDate.parse(dataSenseFormat);  // Use parse instead of format

            // Comprova si hi ha algun camp buit
            if (nom.isEmpty() || descripcio.isEmpty() || ubicacio.isEmpty() || durada.isEmpty()) {
                // Gestionar l'error quan algun camp està buit
                GestorErrors.displayError("Cal omplir tots els camps abans d'afegir un nou esdeveniment.");
            } else {

                // Crea un objecte nouEsdevenimentM amb les dades del formulari
                nouEsdevenimentM nouesdeveniment = new nouEsdevenimentM();
                nouesdeveniment.setNom(nom);
                nouesdeveniment.setDescripcio(descripcio);
                nouesdeveniment.setData(data);
                nouesdeveniment.setAforament(aforament);
                nouesdeveniment.setDurada(durada);
                nouesdeveniment.setUbicacio(ubicacio);
                nouesdeveniment.setCreador_id(AuthorizationM.getInstance().getId());

                // Crida al mètode afegeixEsdeveniment a EsdevenimentC
                esdevenimentC.afegeixEsdeveniment(nouesdeveniment);
            }

        } catch (NumberFormatException e) {
            // Gestiona l'error en cas de que no sigui un valor numèric
            GestorErrors.displayError("Entrada no vàlida per a valors numèrics");
        } catch (ParseException ex) {
            Logger.getLogger(EsdevenimentV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mètode per eliminar un esdeveniment
     */
    public void eliminarEsdeveniment() {
        // Obtenir l'índex de la fila seleccionada
        int filaSeleccionada = taulaEsdeveniments.getSelectedRow();

        // Comprovar si s'ha seleccionat una fila
        if (filaSeleccionada != -1) {
            // Obtenir l'ID de l'esdeveniment de la fila seleccionada
            int idEsdeveniment = (int) taulaEsdeveniments.getValueAt(filaSeleccionada, 0);

            // Cridar al mètode eliminarEsdeveniment a EsdevenimentC amb aquest ID
            esdevenimentC.eliminarEsdeveniment(idEsdeveniment);

            // Actualitzar la taula després de l'eliminació
            esdeveniments = esdevenimentC.getEsdeveniments();
            actualitzaTaulaAmbEsdeveniments(esdeveniments);
        } else {
            // Si no s'ha seleccionat cap fila, mostrar un missatge d'error
            GestorErrors.displayError("Cal seleccionar un esdeveniment per eliminar-lo.");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        taulaEsdeveniments = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        botoBuscaEsdeveniment = new javax.swing.JButton();
        botoNetejaEsdeveniment = new javax.swing.JButton();
        nomLabel1 = new javax.swing.JLabel();
        aforamentBoto = new javax.swing.JButton();
        dataBoto = new javax.swing.JButton();
        ubicacioBoto = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        nomLabel = new javax.swing.JLabel();
        descripcioLabel = new javax.swing.JLabel();
        ubicacioLabel = new javax.swing.JLabel();
        aforamentLabel = new javax.swing.JLabel();
        duradaLabel = new javax.swing.JLabel();
        dataLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nomText = new javax.swing.JTextField();
        separadorLabel = new javax.swing.JLabel();
        descripcioText = new javax.swing.JTextField();
        ubicacioText = new javax.swing.JTextField();
        aforamentText = new javax.swing.JTextField();
        duradaText = new javax.swing.JTextField();
        dataText = new javax.swing.JTextField();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0); // Set minutes to 00
        date = calendar.getTime();

        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        horaSpinner = new javax.swing.JSpinner(sm);
        horaLabel = new javax.swing.JLabel();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jPanel4 = new javax.swing.JPanel();
        netejaBoto = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        editarBoto = new javax.swing.JButton();
        afegirBoto = new javax.swing.JButton();
        eliminarBoto = new javax.swing.JButton();
        buscadorPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taulaAssistents = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(217, 4, 41));

        buscadorPanel.setBackground(new java.awt.Color(217, 4, 41));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N

        taulaEsdeveniments.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        taulaEsdeveniments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Data", "Aforament", "Durada", "Ubicació"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taulaEsdeveniments.getTableHeader().setReorderingAllowed(false);
        taulaEsdeveniments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaEsdevenimentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taulaEsdeveniments);
        taulaEsdeveniments.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (taulaEsdeveniments.getColumnModel().getColumnCount() > 0) {
            taulaEsdeveniments.getColumnModel().getColumn(0).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(0).setPreferredWidth(10);
            taulaEsdeveniments.getColumnModel().getColumn(0).setHeaderValue("ID");
            taulaEsdeveniments.getColumnModel().getColumn(1).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(1).setPreferredWidth(200);
            taulaEsdeveniments.getColumnModel().getColumn(2).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(2).setPreferredWidth(250);
            taulaEsdeveniments.getColumnModel().getColumn(3).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(3).setPreferredWidth(10);
            taulaEsdeveniments.getColumnModel().getColumn(4).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(4).setPreferredWidth(10);
            taulaEsdeveniments.getColumnModel().getColumn(4).setHeaderValue("Durada");
            taulaEsdeveniments.getColumnModel().getColumn(5).setResizable(false);
            taulaEsdeveniments.getColumnModel().getColumn(5).setPreferredWidth(100);
            taulaEsdeveniments.getColumnModel().getColumn(5).setHeaderValue("Ubicació");
        }
        taulaEsdeveniments.setRowHeight(40);

        jPanel6.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
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

        botoBuscaEsdeveniment.setBackground(new java.awt.Color(43, 45, 66));
        botoBuscaEsdeveniment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/lupaB.png"))); // NOI18N
        botoBuscaEsdeveniment.setBorder(null);
        botoBuscaEsdeveniment.setBorderPainted(false);
        botoBuscaEsdeveniment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoBuscaEsdevenimentActionPerformed(evt);
            }
        });

        botoNetejaEsdeveniment.setBackground(new java.awt.Color(43, 45, 66));
        botoNetejaEsdeveniment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/XB.png"))); // NOI18N
        botoNetejaEsdeveniment.setBorder(null);
        botoNetejaEsdeveniment.setBorderPainted(false);
        botoNetejaEsdeveniment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoNetejaEsdevenimentActionPerformed(evt);
            }
        });

        nomLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        nomLabel1.setForeground(new java.awt.Color(237, 242, 244));
        nomLabel1.setText("Ordenar per:");

        aforamentBoto.setBackground(new java.awt.Color(43, 45, 66));
        aforamentBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        aforamentBoto.setForeground(new java.awt.Color(237, 242, 244));
        aforamentBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/aforament.png"))); // NOI18N
        aforamentBoto.setText("  AFORAMENT");
        aforamentBoto.setMaximumSize(new java.awt.Dimension(150, 31));
        aforamentBoto.setMinimumSize(new java.awt.Dimension(150, 31));
        aforamentBoto.setPreferredSize(new java.awt.Dimension(200, 31));
        aforamentBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aforamentBotoActionPerformed(evt);
            }
        });

        dataBoto.setBackground(new java.awt.Color(43, 45, 66));
        dataBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        dataBoto.setForeground(new java.awt.Color(237, 242, 244));
        dataBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/data_esdeveniment.png"))); // NOI18N
        dataBoto.setText("  DATA");
        dataBoto.setMaximumSize(new java.awt.Dimension(150, 31));
        dataBoto.setMinimumSize(new java.awt.Dimension(150, 31));
        dataBoto.setPreferredSize(new java.awt.Dimension(200, 31));
        dataBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBotoActionPerformed(evt);
            }
        });

        ubicacioBoto.setBackground(new java.awt.Color(43, 45, 66));
        ubicacioBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        ubicacioBoto.setForeground(new java.awt.Color(237, 242, 244));
        ubicacioBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/ubicacio2.png"))); // NOI18N
        ubicacioBoto.setText("  UBICACIO");
        ubicacioBoto.setMaximumSize(new java.awt.Dimension(150, 31));
        ubicacioBoto.setMinimumSize(new java.awt.Dimension(150, 31));
        ubicacioBoto.setPreferredSize(new java.awt.Dimension(200, 31));
        ubicacioBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicacioBotoActionPerformed(evt);
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
                                .addComponent(botoBuscaEsdeveniment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aforamentBoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ubicacioBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botoNetejaEsdeveniment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buscadorPanelLayout.setVerticalGroup(
            buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(botoBuscaEsdeveniment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aforamentBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubicacioBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botoNetejaEsdeveniment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanelLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243))))
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

        nomLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        nomLabel.setForeground(new java.awt.Color(237, 242, 244));
        nomLabel.setText("Nom");

        descripcioLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        descripcioLabel.setForeground(new java.awt.Color(237, 242, 244));
        descripcioLabel.setText("Descripció");

        ubicacioLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        ubicacioLabel.setForeground(new java.awt.Color(237, 242, 244));
        ubicacioLabel.setText("Ubicació");

        aforamentLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        aforamentLabel.setForeground(new java.awt.Color(237, 242, 244));
        aforamentLabel.setText("Aforament");

        duradaLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        duradaLabel.setForeground(new java.awt.Color(237, 242, 244));
        duradaLabel.setText("Durada");

        dataLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        dataLabel.setForeground(new java.awt.Color(237, 242, 244));
        dataLabel.setText("Data");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(237, 242, 244));
        jLabel14.setText("ESDEVENIMENT I ASSISTENTS");

        nomText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        nomText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        nomText.setPreferredSize(new java.awt.Dimension(300, 28));

        separadorLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        separadorLabel.setForeground(new java.awt.Color(237, 242, 244));
        separadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        separadorLabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");

        descripcioText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        descripcioText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        descripcioText.setPreferredSize(new java.awt.Dimension(300, 28));

        ubicacioText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        ubicacioText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ubicacioText.setPreferredSize(new java.awt.Dimension(300, 28));

        aforamentText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        aforamentText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        aforamentText.setPreferredSize(new java.awt.Dimension(300, 28));

        duradaText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        duradaText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        duradaText.setPreferredSize(new java.awt.Dimension(300, 28));

        dataText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        dataText.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        dataText.setPreferredSize(new java.awt.Dimension(300, 28));

        // Custom editor to format minutes as "00"
        JSpinner.DateEditor de = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(de);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) horaSpinner.getEditor();
        horaSpinner.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N

        horaLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        horaLabel.setForeground(new java.awt.Color(237, 242, 244));
        horaLabel.setText("Hora");

        jCalendar2.setBackground(new java.awt.Color(217, 4, 41));
        jCalendar2.setDecorationBackgroundColor(new java.awt.Color(217, 4, 41));
        jCalendar2.setDecorationBackgroundVisible(false);
        jCalendar2.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        jCalendar2.setSundayForeground(new java.awt.Color(43, 45, 66));
        jCalendar2.setWeekOfYearVisible(false);
        jCalendar2.setWeekdayForeground(new java.awt.Color(242, 242, 242));
        // Access the JDayChooser component
        JDayChooser dayChooser = jCalendar2.getDayChooser();

        // Add a PropertyChangeListener to the JDayChooser
        dayChooser.addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Get the selected date
                Date selectedDate = jCalendar2.getDate();

                // Format the date in dd:MM:yy format
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                String formattedDate = dateFormat.format(selectedDate);

                // Update the JTextField with the formatted date
                dataText.setText(formattedDate);
            }
        });

        for (int i = 0; i < jCalendar2.getComponentCount(); i++)  {
            if (jCalendar2.getComponent(i) instanceof JDayChooser) {
                JDayChooser chooser = ((JDayChooser) jCalendar2.getComponent( i ) );
                JPanel panel = (JPanel) chooser.getComponent(0);
                // the following line changes the color of the background behind the buttons
                panel.setBackground(new Color(217, 4, 41));
                // the for loop below changes the color of the buttons themselves
                //for (int y = 0; y < panel.getComponentCount(); y++) {
                    //    panel.getComponent(y).setBackground(Color.BLACK);
                    //}
                break; // leave the for loop, we're done
            }
        }

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCalendar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(infoPanelLayout.createSequentialGroup()
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nomLabel)
                                            .addComponent(descripcioLabel)
                                            .addComponent(ubicacioLabel)
                                            .addComponent(aforamentLabel)
                                            .addComponent(duradaLabel))
                                        .addGap(38, 38, 38)
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(duradaText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(aforamentText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ubicacioText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(descripcioText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nomText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(infoPanelLayout.createSequentialGroup()
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dataLabel)
                                            .addComponent(horaLabel))
                                        .addGap(110, 110, 110)
                                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(horaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dataText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel14)))
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
                            .addComponent(nomText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcioText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcioLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ubicacioText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ubicacioLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aforamentText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aforamentLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duradaText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(duradaLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataLabel)
                            .addComponent(dataText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(217, 4, 41));

        netejaBoto.setBackground(new java.awt.Color(43, 45, 66));
        netejaBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        netejaBoto.setForeground(new java.awt.Color(237, 242, 244));
        netejaBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/buida2.png"))); // NOI18N
        netejaBoto.setText(" BUIDA FORMULARI");
        netejaBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netejaBotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(netejaBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(netejaBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(217, 4, 41));

        editarBoto.setBackground(new java.awt.Color(43, 45, 66));
        editarBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        editarBoto.setForeground(new java.awt.Color(237, 242, 244));
        editarBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/editar2.png"))); // NOI18N
        editarBoto.setText(" EDITAR");
        editarBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBotoActionPerformed(evt);
            }
        });

        afegirBoto.setBackground(new java.awt.Color(43, 45, 66));
        afegirBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        afegirBoto.setForeground(new java.awt.Color(237, 242, 244));
        afegirBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/afegir2.png"))); // NOI18N
        afegirBoto.setText(" AFEGIR");
        afegirBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afegirBotoActionPerformed(evt);
            }
        });

        eliminarBoto.setBackground(new java.awt.Color(43, 45, 66));
        eliminarBoto.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        eliminarBoto.setForeground(new java.awt.Color(237, 242, 244));
        eliminarBoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/eliminar2.png"))); // NOI18N
        eliminarBoto.setText(" ELIMINAR");
        eliminarBoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(editarBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(afegirBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminarBoto)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afegirBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarBoto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 87, Short.MAX_VALUE))
        );

        buscadorPanel1.setBackground(new java.awt.Color(217, 4, 41));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N

        taulaAssistents.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        taulaAssistents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Cognoms", "Telèfon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taulaAssistents.getTableHeader().setReorderingAllowed(false);
        taulaAssistents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaAssistentsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(taulaAssistents);
        taulaAssistents.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (taulaAssistents.getColumnModel().getColumnCount() > 0) {
            taulaAssistents.getColumnModel().getColumn(0).setResizable(false);
            taulaAssistents.getColumnModel().getColumn(0).setPreferredWidth(200);
            taulaAssistents.getColumnModel().getColumn(1).setResizable(false);
            taulaAssistents.getColumnModel().getColumn(1).setPreferredWidth(250);
            taulaAssistents.getColumnModel().getColumn(2).setResizable(false);
            taulaAssistents.getColumnModel().getColumn(2).setPreferredWidth(10);
        }
        taulaAssistents.setRowHeight(40);

        jPanel12.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(237, 242, 244));
        jLabel15.setText("ASSISTENTS A L'ESDEVENIMENT");

        javax.swing.GroupLayout buscadorPanel1Layout = new javax.swing.GroupLayout(buscadorPanel1);
        buscadorPanel1.setLayout(buscadorPanel1Layout);
        buscadorPanel1Layout.setHorizontalGroup(
            buscadorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(buscadorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(buscadorPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buscadorPanel1Layout.setVerticalGroup(
            buscadorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buscadorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscadorPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscadorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buscadorPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(buscadorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscadorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void taulaEsdevenimentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaEsdevenimentsMouseClicked
        ompleFormulariAmbDadesEsdeveniment();

    }//GEN-LAST:event_taulaEsdevenimentsMouseClicked

    private void botoBuscaEsdevenimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBuscaEsdevenimentActionPerformed
        esdeveniments = esdevenimentC.getEsdeveniments();
        actualitzaTaulaAmbEsdeveniments(esdeveniments);
    }//GEN-LAST:event_botoBuscaEsdevenimentActionPerformed

    private void netejaBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netejaBotoActionPerformed
        buidaFormulari();
    }//GEN-LAST:event_netejaBotoActionPerformed

    private void editarBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBotoActionPerformed
        editarEsdeveniment();
    }//GEN-LAST:event_editarBotoActionPerformed

    private void afegirBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afegirBotoActionPerformed
        afegirEsdeveniment();
    }//GEN-LAST:event_afegirBotoActionPerformed

    private void eliminarBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBotoActionPerformed
        eliminarEsdeveniment();
    }//GEN-LAST:event_eliminarBotoActionPerformed

    private void taulaAssistentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaAssistentsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_taulaAssistentsMouseClicked

    private void botoNetejaEsdevenimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoNetejaEsdevenimentActionPerformed
        buidaFormulari();
        buidaTaula();
    }//GEN-LAST:event_botoNetejaEsdevenimentActionPerformed

    private void aforamentBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aforamentBotoActionPerformed
        esdeveniments = esdevenimentC.getEsdevenimentsPerOrdre("aforament");
        actualitzaTaulaAmbEsdeveniments(esdeveniments);
    }//GEN-LAST:event_aforamentBotoActionPerformed

    private void dataBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBotoActionPerformed
        esdeveniments = esdevenimentC.getEsdevenimentsPerOrdre("data");
        actualitzaTaulaAmbEsdeveniments(esdeveniments);
    }//GEN-LAST:event_dataBotoActionPerformed

    private void ubicacioBotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacioBotoActionPerformed
        esdeveniments = esdevenimentC.getEsdevenimentsPerOrdre("ubicacio");
        actualitzaTaulaAmbEsdeveniments(esdeveniments);
    }//GEN-LAST:event_ubicacioBotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton afegirBoto;
    private javax.swing.JButton aforamentBoto;
    private javax.swing.JLabel aforamentLabel;
    private javax.swing.JTextField aforamentText;
    private javax.swing.JButton botoBuscaEsdeveniment;
    private javax.swing.JButton botoNetejaEsdeveniment;
    private javax.swing.JPanel buscadorPanel;
    private javax.swing.JPanel buscadorPanel1;
    private javax.swing.JButton dataBoto;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JTextField dataText;
    private javax.swing.JLabel descripcioLabel;
    private javax.swing.JTextField descripcioText;
    private javax.swing.JLabel duradaLabel;
    private javax.swing.JTextField duradaText;
    private javax.swing.JButton editarBoto;
    private javax.swing.JButton eliminarBoto;
    private javax.swing.JLabel horaLabel;
    private javax.swing.JSpinner horaSpinner;
    private javax.swing.JPanel infoPanel;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton netejaBoto;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JLabel nomLabel1;
    private javax.swing.JTextField nomText;
    private javax.swing.JLabel separadorLabel;
    private javax.swing.JTable taulaAssistents;
    private javax.swing.JTable taulaEsdeveniments;
    private javax.swing.JButton ubicacioBoto;
    private javax.swing.JLabel ubicacioLabel;
    private javax.swing.JTextField ubicacioText;
    // End of variables declaration//GEN-END:variables
}
