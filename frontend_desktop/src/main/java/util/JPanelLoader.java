package util;

import javax.swing.JPanel;

/**
 * Classe encarregada de carregar panells (JPanel) a la part esquerra del menú
 *
 * @author Enric
 */
public class JPanelLoader {

    // Mètode per carregar un JPanel a la part esquerra del menú
    public void jPanelLoader(JPanel Main, JPanel setPanel) {
        // Esborra tots els components del JPanel principal (Main)
        Main.removeAll();

        // Utilitza GroupLayout per gestionar el disseny del JPanel principal (Main)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);
        Main.setLayout(layout);

        // Configuració de l'horitzontal i vertical del GroupLayout
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        // Demana al sistema de gestió de la memòria que es reculli
        System.gc();
    }
}
