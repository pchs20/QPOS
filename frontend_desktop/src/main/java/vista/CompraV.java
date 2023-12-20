package vista;

import controlador.ClientC;
import controlador.CompraC;
import controlador.ProducteC;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.AuthorizationM;
import model.ClientM;
import model.novaCompraM;
import model.novaLiniaCompraM;
import model.ProducteM;
import util.GestorErrors;

/**
 * Classe que representa la pantalla del punt de venda
 *
 * @author Enric
 */
public class CompraV extends javax.swing.JPanel {

    private String missatgeBuscadorProductes = "Busca productes o busca per ID";
    private String missatgeBuscadorClient = "Busca client per DNI";

    private ClientC clientC = new ClientC();
    private ClientM clientM = new ClientM();

    ProducteC producteC = new ProducteC();
    private List<ProducteM> producteList;

    List<novaLiniaCompraM> linies;

    // Crea un Map per guardar els ID's del productes i les quantitats
    HashMap<Integer, Integer> quantitatDeProductes = new HashMap<>();

    /**
     * Constructor
     */
    public CompraV() {
        initComponents();
    }

    /**
     * Mètode per mostrar productes a la taula
     */
    public void actualitzaTaulaAmbProductes() {
        DefaultTableModel modelDeTaula = (DefaultTableModel) taulaProductes.getModel();
        modelDeTaula.setRowCount(0); // Esborra les dades existents

        // Crida el mètode getProductes a ProducteC per obtenir les dades actualitzades del producte
        ProducteM producteM = producteC.getProductes();

        if (producteM != null && producteM.getProductes() != null) {

            producteList = Arrays.asList(producteM.getProductes()); // Convert array to List

            // Omple el model de la taula amb les dades de l'array de ProducteM
            for (ProducteM producte : producteM.getProductes()) {
                Object[] dadesFila = {
                    producte.getId(),
                    producte.getNom(),
                    producte.getPreu()
                };
                modelDeTaula.addRow(dadesFila);
            }

        } else {
            GestorErrors.displayError("Error en recuperar les dades del producte.");
        }
    }

    /**
     * Mètode per obtenir i mostrar un producte específic a la taula
     *
     * @param idProducte
     */
    private void actualitzaTaulaAmbProductePerID(int idProducte) {
        // Obté el producte utilitzant el mètode getProducte a ProducteC
        ProducteM producte = producteC.getProducte(idProducte);

        // Comprova si el producte no és nul
        if (producte != null) {

            // Esborra el model de la taula
            DefaultTableModel modelDeTaula = (DefaultTableModel) taulaProductes.getModel();
            modelDeTaula.setRowCount(0);

            // Omple la taula amb les dades del producte individual
            Object[] dadesFila = {
                producte.getId(),
                producte.getNom(),
                producte.getPreu()
            };
            modelDeTaula.addRow(dadesFila);

            // Guarda el producte a la llista
            producteList = new ArrayList<>(Arrays.asList(producte));
            producteList.add(producte);
        } else {
            GestorErrors.displayError("Error en recuperar les dades del producte amb ID: " + idProducte);
        }
    }

    /**
     * Mètode que actualitza l'import total
     */
    private void actualitzaTotal() {
        DefaultTableModel model = (DefaultTableModel) taulaProductesTicket.getModel();

        double total = 0.0;

        // Fem servir DecimalFormat per un format consistent
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (int row = 0; row < model.getRowCount(); row++) {
            Object preuProducte = model.getValueAt(row, 2);

            if (preuProducte instanceof Number) {
                total += ((Number) preuProducte).doubleValue();
            } else if (preuProducte instanceof String) {
                try {
                    // Canvies comes per punts i suma al total
                    total += Double.parseDouble(((String) preuProducte).replace(",", "."));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        // Actualitza subtotalQuantitat amb format de punts
        subtotalQuantitat.setText(decimalFormat.format(total).replace(",", "."));

        // Actualitza totalQuantitat amb format de punts
        try {
            double cupoQuantitat = Double.parseDouble(cupoText.getText().replace(",", "."));
            double totalQuantitatImport = total - cupoQuantitat;
            totalQuantitat.setText(decimalFormat.format(totalQuantitatImport).replace(",", "."));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode que dona format al camp Entregat
     */
    private void formatEntregatText() {
        String text = entregatText.getText().trim();

        if (text.isEmpty()) {
            entregatText.setText("0.00"); // Si està buit
        } else {
            try {
                double value = Double.parseDouble(text.replace(",", ".")); // Canviem comes per punts
                entregatText.setText(String.format("%.2f", value)); // Format amb dos decimals
            } catch (NumberFormatException ex) {
                entregatText.setText("0.00"); // Valor per defecte
            }
        }
    }

    /**
     * Mètode que gestiona el pagament segons el mètode de pagament (Efectiu o
     * Targeta)
     *
     * @param metodePagament
     */
    private void procesPagament(String metodePagament) {
        try {
            // Comprova si clientM no és nul
            if (clientM == null || clientM.getId() == 0) {
                JOptionPane.showMessageDialog(this, "Client no trobat. Assegura't de seleccionar un client vàlid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Comprova si taulaProductesTicket no és buida
            DefaultTableModel model = (DefaultTableModel) taulaProductesTicket.getModel();
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "El carro de la compra no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Comprova si cupoText no és buit i és un valor decimal vàlid
            if (cupoText.getText().isEmpty()) {
                // Mostra el dialeg per als camps buits
                JOptionPane.showMessageDialog(this, "El camp 'Descompte' no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reemplaça les comes per punts a la cadena d'entrada
            String cupoTextValue = cupoText.getText().replace(",", ".");

            // Correcció dels tipus de dades utilitzant DecimalFormat
            DecimalFormat decimalFormat = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            double descompte = Double.parseDouble(decimalFormat.format(Double.parseDouble(cupoTextValue)));

            // Itera a través de les files de taulaProductesTicket
            for (int row = 0; row < model.getRowCount(); row++) {
                int idProducte = (int) model.getValueAt(row, 0);

                // Actualitza la quantitat en el mapa
                quantitatDeProductes.put(idProducte, quantitatDeProductes.getOrDefault(idProducte, 0) + 1);
            }

            // Crea una llista d'objectes novaLiniaCompraM utilitzant el mapa
            linies = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : quantitatDeProductes.entrySet()) {
                int idProducte = entry.getKey();
                int quantitat = entry.getValue();
                linies.add(new novaLiniaCompraM(idProducte, quantitat));
            }

            // Crea un objecte novaCompraM de mostra
            int client_id = clientM.getId();
            int treballador_id = AuthorizationM.getInstance().getId();

            // Inicialitza les variables específiques de cada mètode de pagament
            double importTotal, entregat, canvi;

            if ("Efectiu".equals(metodePagament)) {
                try {
                    // Comprova si entregatText és un número vàlid
                    entregat = Double.parseDouble(entregatText.getText().replace(",", "."));

                    // Comprova si entregat és més gran que importTotal
                    importTotal = Double.parseDouble(decimalFormat.format(Double.parseDouble(totalQuantitat.getText().replace(",", "."))));
                    canvi = Double.parseDouble(decimalFormat.format(entregat - importTotal));

                    if (canvi < 0) {
                        // Mostra el dialeg per al pagament insuficient
                        JOptionPane.showMessageDialog(this, "L'import entregat no pot ser menor que l'import total.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Mostra el dialeg per al format de número no vàlid
                    JOptionPane.showMessageDialog(this, "L'import entregat ha de ser un número vàlid.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if ("Targeta".equals(metodePagament)) {
                // Per "Targeta," tot l'import es considera com a pagat
                importTotal = Double.parseDouble(decimalFormat.format(Double.parseDouble(totalQuantitat.getText().replace(",", "."))));
                entregat = importTotal;
                canvi = 0;
            } else {
                // Gestionar el mètode de pagament inesperat
                JOptionPane.showMessageDialog(this, "Mètode de pagament no reconegut.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            novaCompraM novaCompra = new novaCompraM(client_id, treballador_id, linies, metodePagament,
                    Double.parseDouble(decimalFormat.format(entregat)),
                    Double.parseDouble(decimalFormat.format(canvi)),
                    Double.parseDouble(decimalFormat.format(descompte)));

            // Crea una instància de CompraC i realitza la sol·licitud API
            CompraC compraController = new CompraC();
            compraController.creaCompra(novaCompra);

            // Mostra el dialeg d'èxit amb la informació del canvi
            if (canvi > 0) {
                JOptionPane.showMessageDialog(this, String.format("Recorda entregar %.2f al client.", canvi), "Compra realitzada amb èxit", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Compra realitzada amb èxit", "Compra realitzada amb èxit", JOptionPane.INFORMATION_MESSAGE);
            }

            // Reinicia la pantalla per a una nova compra
            reiniciaCompra();

        } catch (NumberFormatException e) {
            // Gestionar l'excepció de format de número si és necessari
            e.printStackTrace();
        }
    }

    /**
     * Mètode per reiniciar la pantalla de punt de venda per a una nova compra
     */
    private void reiniciaCompra() {
        // Esborra taulaProductesTicket
        DefaultTableModel model = (DefaultTableModel) taulaProductesTicket.getModel();
        model.setRowCount(0);

        // Esborra taulaProductes
        DefaultTableModel model2 = (DefaultTableModel) taulaProductes.getModel();
        model2.setRowCount(0);

        clientM = null;

        // Crea una nova instància de l'ArrayList
        producteList = new ArrayList<>();

        // Crea una nova instància del HashMap
        quantitatDeProductes = new HashMap<>();

        linies.clear();

        entregatText.setText("ENTREGAT");

        buscadorClient.setText(missatgeBuscadorClient);
        buscadorProductes.setText(missatgeBuscadorProductes);

        cupoText.setText("0.00");

        actualitzaTotal();
    }

    public void afegirProducteTaulaTicket() {
        // Selecciona el producte de la taula
        int filaSeleccionada = taulaProductes.getSelectedRow();

        if (filaSeleccionada != -1) {
            DefaultTableModel sourceTableModel = (DefaultTableModel) taulaProductes.getModel();
            DefaultTableModel destinationTableModel = (DefaultTableModel) taulaProductesTicket.getModel();

            Object[] dadesFila = new Object[3];
            dadesFila[0] = sourceTableModel.getValueAt(filaSeleccionada, 0);
            dadesFila[1] = sourceTableModel.getValueAt(filaSeleccionada, 1);
            dadesFila[2] = sourceTableModel.getValueAt(filaSeleccionada, 2);

            destinationTableModel.addRow(dadesFila);
        }
        actualitzaTotal();
    }

    public void eliminarProducteTaulaTicket() {
        // Eliminar el producte del ticket        
        int filaSeleccionada = taulaProductesTicket.getSelectedRow();

        if (filaSeleccionada != -1) {
            DefaultTableModel destinationTableModel = (DefaultTableModel) taulaProductesTicket.getModel();

            destinationTableModel.removeRow(filaSeleccionada);
        }
        actualitzaTotal();
    }

    /**
     * Mètode que busca el client per DNI i omple el camp buscadorClient amb el
     * nom i cognoms
     */
    public void buscaClientPerDNI() {
        // Obtenir el text del camp de text BuscadorClient
        String dniClient = buscadorClient.getText();

        // Comprovar si el text no és buit 
        if (!dniClient.isEmpty() && !dniClient.equals(missatgeBuscadorClient)) {
            try {
                // Crida al mètode per mostrar el client individual al buscador
                clientM = clientC.getClientPerDNI(dniClient);
                // Mostra el nom i cognoms si el client no és null
                if (clientM != null) {
                    buscadorClient.setText(clientM.getNom() + " " + clientM.getCognoms());
                } else {
                    // Si el client és null, deixa el buscadorClient en blanc
                    buscadorClient.setText(missatgeBuscadorClient);
                    GestorErrors.displayError("Client no trobat a la base de dades.");
                }
            } catch (NumberFormatException e) {
                // Gestionar el cas en què dniClient no sigui una entrada vàlida
                System.out.println("Format de dniClient no vàlid.");
            }
        }
    }

    /**
     * Mètode per determinar si omplim la taula amb tots els productes o amb un
     * producte segons el text a buscadorProductes
     */
    public void buscaProductes() {
        // Obtenir el text del camp de text BuscadorProductes
        String idProducteText = buscadorProductes.getText();

        // Comprovar si el text no és buit 
        if (!idProducteText.isEmpty()) {
            try {
                // Intenta analitzar el text per obtenir la identificació del producte
                int idProducte = Integer.parseInt(idProducteText);

                // Crida al mètode per mostrar el producte individual a la taula
                actualitzaTaulaAmbProductePerID(idProducte);
            } catch (NumberFormatException e) {
                // Gestionar el cas en què idProducteText no sigui un enter vàlid
                System.out.println("Format d'ID de producte no vàlid. Mostrant tots els productes en lloc d'això.");

                // Mostra tots els productes
                actualitzaTaulaAmbProductes();
            }
        } else {
            // Si el text és buit, obtenir tots els productes i omplir la taula
            actualitzaTaulaAmbProductes();
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

        productePanel = new javax.swing.JPanel();
        buscadorProductes = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taulaProductes = new javax.swing.JTable();
        separador1 = new javax.swing.JPanel();
        separador2 = new javax.swing.JPanel();
        separador3 = new javax.swing.JPanel();
        botoNetejaBuscadorProducte = new javax.swing.JButton();
        botoBuscaProducte = new javax.swing.JButton();
        operacionsPanel = new javax.swing.JPanel();
        clientPanel = new javax.swing.JPanel();
        buscadorClient = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taulaProductesTicket = new javax.swing.JTable();
        separador4 = new javax.swing.JPanel();
        botoBuscaClient = new javax.swing.JButton();
        botoNetejaBuscadorClient = new javax.swing.JButton();
        calculsPanel = new javax.swing.JPanel();
        subtotalQuantitat = new javax.swing.JLabel();
        cupoText = new javax.swing.JTextField();
        subtotalLabel = new javax.swing.JLabel();
        cupoQuantitat = new javax.swing.JLabel();
        liniesDiscontinues = new javax.swing.JLabel();
        totalQuantitat = new javax.swing.JLabel();
        totalText = new javax.swing.JLabel();
        subtotalEur = new javax.swing.JLabel();
        cupoEur = new javax.swing.JLabel();
        totalEur = new javax.swing.JLabel();
        pagamentPanel = new javax.swing.JPanel();
        pagamentEfectiu = new javax.swing.JButton();
        pagamentTargeta = new javax.swing.JButton();
        entregatEur = new javax.swing.JLabel();
        entregatText = new javax.swing.JTextField();

        setBackground(new java.awt.Color(217, 4, 41));

        productePanel.setBackground(new java.awt.Color(217, 4, 41));

        buscadorProductes.setBackground(new java.awt.Color(237, 242, 244));
        buscadorProductes.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        buscadorProductes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        buscadorProductes.setText("Busca productes o busca per ID");
        buscadorProductes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                buscadorProductesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                buscadorProductesFocusLost(evt);
            }
        });

        taulaProductes.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        taulaProductes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codi", "Nom", "Preu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taulaProductes.getTableHeader().setReorderingAllowed(false);
        taulaProductes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaProductesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taulaProductes);
        if (taulaProductes.getColumnModel().getColumnCount() > 0) {
            taulaProductes.getColumnModel().getColumn(0).setPreferredWidth(20);
            taulaProductes.getColumnModel().getColumn(1).setPreferredWidth(500);
            taulaProductes.getColumnModel().getColumn(2).setPreferredWidth(40);
        }
        taulaProductes.setRowHeight(40);

        separador1.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout separador1Layout = new javax.swing.GroupLayout(separador1);
        separador1.setLayout(separador1Layout);
        separador1Layout.setHorizontalGroup(
            separador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separador1Layout.setVerticalGroup(
            separador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        separador2.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout separador2Layout = new javax.swing.GroupLayout(separador2);
        separador2.setLayout(separador2Layout);
        separador2Layout.setHorizontalGroup(
            separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );
        separador2Layout.setVerticalGroup(
            separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        separador3.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout separador3Layout = new javax.swing.GroupLayout(separador3);
        separador3.setLayout(separador3Layout);
        separador3Layout.setHorizontalGroup(
            separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        separador3Layout.setVerticalGroup(
            separador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        botoNetejaBuscadorProducte.setBackground(new java.awt.Color(43, 45, 66));
        botoNetejaBuscadorProducte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/XB.png"))); // NOI18N
        botoNetejaBuscadorProducte.setBorder(null);
        botoNetejaBuscadorProducte.setBorderPainted(false);

        botoBuscaProducte.setBackground(new java.awt.Color(43, 45, 66));
        botoBuscaProducte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/lupaB.png"))); // NOI18N
        botoBuscaProducte.setBorder(null);
        botoBuscaProducte.setBorderPainted(false);
        botoBuscaProducte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoBuscaProducteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productePanelLayout = new javax.swing.GroupLayout(productePanel);
        productePanel.setLayout(productePanelLayout);
        productePanelLayout.setHorizontalGroup(
            productePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(productePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productePanelLayout.createSequentialGroup()
                        .addComponent(botoBuscaProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscadorProductes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoNetejaBuscadorProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productePanelLayout.setVerticalGroup(
            productePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscadorProductes, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(botoNetejaBuscadorProducte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botoBuscaProducte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(productePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );

        operacionsPanel.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout operacionsPanelLayout = new javax.swing.GroupLayout(operacionsPanel);
        operacionsPanel.setLayout(operacionsPanelLayout);
        operacionsPanelLayout.setHorizontalGroup(
            operacionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        operacionsPanelLayout.setVerticalGroup(
            operacionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        clientPanel.setBackground(new java.awt.Color(217, 4, 41));
        clientPanel.setPreferredSize(new java.awt.Dimension(544, 425));

        buscadorClient.setBackground(new java.awt.Color(237, 242, 244));
        buscadorClient.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        buscadorClient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        buscadorClient.setText("Busca client per DNI");
        buscadorClient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                buscadorClientFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                buscadorClientFocusLost(evt);
            }
        });

        taulaProductesTicket.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        taulaProductesTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codi", "Nom", "Preu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taulaProductesTicket.getTableHeader().setReorderingAllowed(false);
        taulaProductesTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaProductesTicketMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(taulaProductesTicket);
        if (taulaProductesTicket.getColumnModel().getColumnCount() > 0) {
            taulaProductesTicket.getColumnModel().getColumn(0).setResizable(false);
            taulaProductesTicket.getColumnModel().getColumn(0).setPreferredWidth(3);
            taulaProductesTicket.getColumnModel().getColumn(1).setResizable(false);
            taulaProductesTicket.getColumnModel().getColumn(1).setPreferredWidth(250);
            taulaProductesTicket.getColumnModel().getColumn(2).setResizable(false);
            taulaProductesTicket.getColumnModel().getColumn(2).setPreferredWidth(20);
        }
        taulaProductesTicket.setRowHeight(40);

        separador4.setBackground(new java.awt.Color(217, 4, 41));

        javax.swing.GroupLayout separador4Layout = new javax.swing.GroupLayout(separador4);
        separador4.setLayout(separador4Layout);
        separador4Layout.setHorizontalGroup(
            separador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separador4Layout.setVerticalGroup(
            separador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
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

        calculsPanel.setBackground(new java.awt.Color(217, 4, 41));

        subtotalQuantitat.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        subtotalQuantitat.setForeground(new java.awt.Color(237, 242, 244));
        subtotalQuantitat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        subtotalQuantitat.setText("0");
        subtotalQuantitat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cupoText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        cupoText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cupoText.setText("0.00");
        cupoText.setToolTipText("VALIDA CUPÓ");

        subtotalLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        subtotalLabel.setForeground(new java.awt.Color(237, 242, 244));
        subtotalLabel.setText("SUBTOTAL");

        cupoQuantitat.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        cupoQuantitat.setForeground(new java.awt.Color(237, 242, 244));
        cupoQuantitat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cupoQuantitat.setText("0");
        cupoQuantitat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        liniesDiscontinues.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        liniesDiscontinues.setForeground(new java.awt.Color(237, 242, 244));
        liniesDiscontinues.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        liniesDiscontinues.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");

        totalQuantitat.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        totalQuantitat.setForeground(new java.awt.Color(237, 242, 244));
        totalQuantitat.setText("0");

        totalText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        totalText.setForeground(new java.awt.Color(237, 242, 244));
        totalText.setText("TOTAL");

        subtotalEur.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        subtotalEur.setForeground(new java.awt.Color(237, 242, 244));
        subtotalEur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subtotalEur.setText("€");
        subtotalEur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cupoEur.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        cupoEur.setForeground(new java.awt.Color(237, 242, 244));
        cupoEur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cupoEur.setText("€");
        cupoEur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        totalEur.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        totalEur.setForeground(new java.awt.Color(237, 242, 244));
        totalEur.setText("€");

        javax.swing.GroupLayout calculsPanelLayout = new javax.swing.GroupLayout(calculsPanel);
        calculsPanel.setLayout(calculsPanelLayout);
        calculsPanelLayout.setHorizontalGroup(
            calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculsPanelLayout.createSequentialGroup()
                .addComponent(liniesDiscontinues, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculsPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calculsPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(subtotalLabel))
                    .addComponent(cupoText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subtotalQuantitat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cupoQuantitat, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cupoEur, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subtotalEur, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(totalText)
                .addGap(99, 99, 99)
                .addComponent(totalQuantitat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalEur)
                .addGap(38, 38, 38))
        );
        calculsPanelLayout.setVerticalGroup(
            calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalQuantitat)
                    .addComponent(subtotalLabel)
                    .addComponent(subtotalEur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cupoText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cupoQuantitat)
                    .addComponent(cupoEur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(liniesDiscontinues)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(calculsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalQuantitat)
                    .addComponent(totalEur)
                    .addComponent(totalText)))
        );

        javax.swing.GroupLayout clientPanelLayout = new javax.swing.GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separador4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(clientPanelLayout.createSequentialGroup()
                                .addComponent(botoBuscaClient, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscadorClient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botoNetejaBuscadorClient, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(86, 86, 86))
                    .addGroup(clientPanelLayout.createSequentialGroup()
                        .addComponent(calculsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        clientPanelLayout.setVerticalGroup(
            clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscadorClient, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(botoBuscaClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botoNetejaBuscadorClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calculsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pagamentPanel.setBackground(new java.awt.Color(217, 4, 41));

        pagamentEfectiu.setBackground(new java.awt.Color(43, 45, 66));
        pagamentEfectiu.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        pagamentEfectiu.setForeground(new java.awt.Color(237, 242, 244));
        pagamentEfectiu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/efectiu_1.png"))); // NOI18N
        pagamentEfectiu.setText("EFECTIU");
        pagamentEfectiu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagamentEfectiuActionPerformed(evt);
            }
        });

        pagamentTargeta.setBackground(new java.awt.Color(43, 45, 66));
        pagamentTargeta.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 24)); // NOI18N
        pagamentTargeta.setForeground(new java.awt.Color(237, 242, 244));
        pagamentTargeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/tarjeta_1.png"))); // NOI18N
        pagamentTargeta.setText("TARGETA");
        pagamentTargeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagamentTargetaActionPerformed(evt);
            }
        });

        entregatEur.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 36)); // NOI18N
        entregatEur.setForeground(new java.awt.Color(237, 242, 244));
        entregatEur.setText("€");

        entregatText.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 18)); // NOI18N
        entregatText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        entregatText.setText("ENTREGAT");
        entregatText.setToolTipText("Efectiu entregat pel client");
        entregatText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                entregatTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                entregatTextFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pagamentPanelLayout = new javax.swing.GroupLayout(pagamentPanel);
        pagamentPanel.setLayout(pagamentPanelLayout);
        pagamentPanelLayout.setHorizontalGroup(
            pagamentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagamentPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pagamentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pagamentPanelLayout.createSequentialGroup()
                        .addComponent(entregatText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entregatEur))
                    .addComponent(pagamentEfectiu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(pagamentTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pagamentPanelLayout.setVerticalGroup(
            pagamentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagamentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pagamentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(entregatText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entregatEur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pagamentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagamentEfectiu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pagamentTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(operacionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clientPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pagamentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pagamentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(operacionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscadorClientFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorClientFocusGained
        if (buscadorClient.getText().matches(missatgeBuscadorClient)) {
            buscadorClient.setText("");
        }
    }//GEN-LAST:event_buscadorClientFocusGained

    private void buscadorClientFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorClientFocusLost
        if (buscadorClient.getText().isEmpty()) {
            buscadorClient.setText(missatgeBuscadorClient);
        }
    }//GEN-LAST:event_buscadorClientFocusLost

    private void buscadorProductesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorProductesFocusGained
        buscadorProductes.setText("");
    }//GEN-LAST:event_buscadorProductesFocusGained

    private void buscadorProductesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_buscadorProductesFocusLost
        if (buscadorProductes.getText().isEmpty()) {
            buscadorProductes.setText(missatgeBuscadorProductes);
        }
    }//GEN-LAST:event_buscadorProductesFocusLost

    private void taulaProductesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaProductesMouseClicked
        afegirProducteTaulaTicket();
    }//GEN-LAST:event_taulaProductesMouseClicked

    private void taulaProductesTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaProductesTicketMouseClicked
        eliminarProducteTaulaTicket();
    }//GEN-LAST:event_taulaProductesTicketMouseClicked

    private void botoBuscaClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBuscaClientActionPerformed
        buscaClientPerDNI();
    }//GEN-LAST:event_botoBuscaClientActionPerformed

    private void botoNetejaBuscadorClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoNetejaBuscadorClientActionPerformed
        buscadorClient.setText(missatgeBuscadorClient);
        clientM = null;
        System.out.println("La ID del usuari es: " + AuthorizationM.getInstance().getId());
    }//GEN-LAST:event_botoNetejaBuscadorClientActionPerformed

    private void pagamentEfectiuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagamentEfectiuActionPerformed
        procesPagament("Efectiu");
    }//GEN-LAST:event_pagamentEfectiuActionPerformed

    private void botoBuscaProducteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBuscaProducteActionPerformed
        buscaProductes();
    }//GEN-LAST:event_botoBuscaProducteActionPerformed

    private void entregatTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_entregatTextFocusGained
        if (entregatText.getText().matches("ENTREGAT")) {
            entregatText.setText("");
        }
    }//GEN-LAST:event_entregatTextFocusGained

    private void entregatTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_entregatTextFocusLost
        formatEntregatText();
    }//GEN-LAST:event_entregatTextFocusLost

    private void pagamentTargetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagamentTargetaActionPerformed
        procesPagament("Targeta");
    }//GEN-LAST:event_pagamentTargetaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoBuscaClient;
    private javax.swing.JButton botoBuscaProducte;
    private javax.swing.JButton botoNetejaBuscadorClient;
    private javax.swing.JButton botoNetejaBuscadorProducte;
    private javax.swing.JTextField buscadorClient;
    private javax.swing.JTextField buscadorProductes;
    private javax.swing.JPanel calculsPanel;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JLabel cupoEur;
    private javax.swing.JLabel cupoQuantitat;
    private javax.swing.JTextField cupoText;
    private javax.swing.JLabel entregatEur;
    private javax.swing.JTextField entregatText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel liniesDiscontinues;
    private javax.swing.JPanel operacionsPanel;
    private javax.swing.JButton pagamentEfectiu;
    private javax.swing.JPanel pagamentPanel;
    private javax.swing.JButton pagamentTargeta;
    private javax.swing.JPanel productePanel;
    private javax.swing.JPanel separador1;
    private javax.swing.JPanel separador2;
    private javax.swing.JPanel separador3;
    private javax.swing.JPanel separador4;
    private javax.swing.JLabel subtotalEur;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel subtotalQuantitat;
    private javax.swing.JTable taulaProductes;
    private javax.swing.JTable taulaProductesTicket;
    private javax.swing.JLabel totalEur;
    private javax.swing.JLabel totalQuantitat;
    private javax.swing.JLabel totalText;
    // End of variables declaration//GEN-END:variables
}
