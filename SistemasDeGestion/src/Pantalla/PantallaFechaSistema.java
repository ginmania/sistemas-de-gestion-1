/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaFechaSistema.java
 *
 * Created on 21-nov-2011, 16:35:17
 */
package Pantalla;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

/**
 *
 * @author duende
 */
public class PantallaFechaSistema extends javax.swing.JInternalFrame {

    /** Creates new form PantallaFechaSistema */
    public PantallaFechaSistema() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_FechaSistema = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jbAceptarFecha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fecha Sistema");

        jbAceptarFecha.setText("Aceptar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jd_FechaSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jbAceptarFecha)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jd_FechaSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jbAceptarFecha)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbAceptarFecha;
    private com.toedter.calendar.JDateChooser jd_FechaSistema;
    // End of variables declaration//GEN-END:variables

    public JButton getJbAceptarFecha() {
        return jbAceptarFecha;
    }

    public void setJbAceptarFecha(JButton jbAceptarFecha) {
        this.jbAceptarFecha = jbAceptarFecha;
    }

    public JDateChooser getJd_FechaSistema() {
        return jd_FechaSistema;
    }

    public void setJd_FechaSistema(JDateChooser jd_FechaSistema) {
        this.jd_FechaSistema = jd_FechaSistema;
    }

}
