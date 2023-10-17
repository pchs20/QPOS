package qpos;

/**
 *
 * @author Enric
 */
public class Menu extends javax.swing.JFrame {

    JPanelLoader JPLoader = new JPanelLoader();

    public Menu() {

        initComponents();
        getContentPane().setBackground(new java.awt.Color(217, 4, 41));
        this.setExtendedState(Menu.MAXIMIZED_BOTH);
        Puntdevenda pdv = new Puntdevenda();
        JPLoader.jPanelLoader(pantalla, pdv);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuLateral = new javax.swing.JPanel();
        infoUsuari = new javax.swing.JPanel();
        botoPuntDeVenda = new javax.swing.JToggleButton();
        botoClients = new javax.swing.JToggleButton();
        botoMagatzem = new javax.swing.JToggleButton();
        botoVendes = new javax.swing.JToggleButton();
        pantalla = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 4, 41));

        menuLateral.setBackground(new java.awt.Color(43, 45, 66));
        menuLateral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        infoUsuari.setBackground(new java.awt.Color(43, 45, 66));

        javax.swing.GroupLayout infoUsuariLayout = new javax.swing.GroupLayout(infoUsuari);
        infoUsuari.setLayout(infoUsuariLayout);
        infoUsuariLayout.setHorizontalGroup(
            infoUsuariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        infoUsuariLayout.setVerticalGroup(
            infoUsuariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        botoPuntDeVenda.setBackground(new java.awt.Color(43, 45, 66));
        botoPuntDeVenda.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        botoPuntDeVenda.setForeground(new java.awt.Color(141, 153, 174));
        botoPuntDeVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/punt_de_venda.png"))); // NOI18N
        botoPuntDeVenda.setText("PUNT DE VENDA");
        botoPuntDeVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoPuntDeVenda.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        botoPuntDeVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoPuntDeVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoPuntDeVendaActionPerformed(evt);
            }
        });

        botoClients.setBackground(new java.awt.Color(43, 45, 66));
        botoClients.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        botoClients.setForeground(new java.awt.Color(141, 153, 174));
        botoClients.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/clients_1.png"))); // NOI18N
        botoClients.setText("CLIENTS");
        botoClients.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoClients.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        botoClients.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoClientsActionPerformed(evt);
            }
        });

        botoMagatzem.setBackground(new java.awt.Color(43, 45, 66));
        botoMagatzem.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        botoMagatzem.setForeground(new java.awt.Color(141, 153, 174));
        botoMagatzem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/magatzem_1.png"))); // NOI18N
        botoMagatzem.setText("MAGATZEM");
        botoMagatzem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoMagatzem.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        botoMagatzem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoMagatzem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoMagatzemActionPerformed(evt);
            }
        });

        botoVendes.setBackground(new java.awt.Color(43, 45, 66));
        botoVendes.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        botoVendes.setForeground(new java.awt.Color(141, 153, 174));
        botoVendes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/vendes_1.png"))); // NOI18N
        botoVendes.setText("VENDES");
        botoVendes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoVendes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        botoVendes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoVendes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoVendesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLateralLayout = new javax.swing.GroupLayout(menuLateral);
        menuLateral.setLayout(menuLateralLayout);
        menuLateralLayout.setHorizontalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLateralLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botoPuntDeVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botoClients, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botoMagatzem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botoVendes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoUsuari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        menuLateralLayout.setVerticalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLateralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(infoUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botoPuntDeVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botoClients, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botoMagatzem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botoVendes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pantallaLayout = new javax.swing.GroupLayout(pantalla);
        pantalla.setLayout(pantallaLayout);
        pantallaLayout.setHorizontalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
        );
        pantallaLayout.setVerticalGroup(
            pantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 857, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botoPuntDeVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoPuntDeVendaActionPerformed

        Puntdevenda pdv = new Puntdevenda();
        JPLoader.jPanelLoader(pantalla, pdv);

    }//GEN-LAST:event_botoPuntDeVendaActionPerformed

    private void botoClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoClientsActionPerformed

        Clients cli = new Clients();
        JPLoader.jPanelLoader(pantalla, cli);

    }//GEN-LAST:event_botoClientsActionPerformed

    private void botoMagatzemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoMagatzemActionPerformed

        Magatzem mag = new Magatzem();
        JPLoader.jPanelLoader(pantalla, mag);

    }//GEN-LAST:event_botoMagatzemActionPerformed

    private void botoVendesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoVendesActionPerformed

        Puntdevenda pdv = new Puntdevenda();
        JPLoader.jPanelLoader(pantalla, pdv);

    }//GEN-LAST:event_botoVendesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botoClients;
    private javax.swing.JToggleButton botoMagatzem;
    private javax.swing.JToggleButton botoPuntDeVenda;
    private javax.swing.JToggleButton botoVendes;
    private javax.swing.JPanel infoUsuari;
    private javax.swing.JPanel menuLateral;
    private javax.swing.JPanel pantalla;
    // End of variables declaration//GEN-END:variables
}
