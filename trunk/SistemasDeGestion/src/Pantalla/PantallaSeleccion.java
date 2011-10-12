/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaSeleccion.java
 *
 * Created on 19-sep-2011, 0:41:01
 */
package Pantalla;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author duende
 */
public class PantallaSeleccion extends javax.swing.JInternalFrame {

    /** Creates new form PantallaSeleccion */
    public PantallaSeleccion() {
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

        jlCombo = new javax.swing.JComboBox();
        jlEtiqueta = new javax.swing.JLabel();
        jLabelCantidad = new javax.swing.JLabel();
        jtCantidad = new javax.swing.JTextField();
        jBtnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Seleccion");
        setName(""); // NOI18N

        jlCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione ..." }));

        jlEtiqueta.setText("Producto");

        jLabelCantidad.setText("Cantidad");

        jBtnAceptar.setText("Aceptar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jBtnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEtiqueta)
                            .addComponent(jLabelCantidad))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlEtiqueta))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCantidad)
                    .addComponent(jtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jlCombo.getAccessibleContext().setAccessibleName("cbSelecion");
        jtCantidad.getAccessibleContext().setAccessibleName("jtCantidad");
        jtCantidad.getAccessibleContext().setAccessibleDescription("cantidad");

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jBtnAceptar;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JComboBox jlCombo;
    private javax.swing.JLabel jlEtiqueta;
    private javax.swing.JTextField jtCantidad;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getjBtnAceptar() {
        return jBtnAceptar;
    }

    public void setjBtnAceptar(JButton jBtnAceptar) {
        this.jBtnAceptar = jBtnAceptar;
    }

    public JLabel getjLabelCantidad() {
        return jLabelCantidad;
    }

    public void setjLabelCantidad(JLabel jLabelCantidad) {
        this.jLabelCantidad = jLabelCantidad;
    }

    public JComboBox getJlCombo() {
        return jlCombo;
    }

    public void setJlCombo(Object obj) {
        this.jlCombo.addItem(obj);
    }

    public String getJlEtiqueta() {
        return jlEtiqueta.getText();
    }

    public void setJlEtiqueta(String Etiqueta) {
        this.jlEtiqueta.setText(Etiqueta);
    }

    public JTextField getJtCantidad() {
        return jtCantidad;
    }
    
    public String getCantidad() {
        return jtCantidad.getText();
    }

    public void setJtCantidad(int Cantidad) {
        this.jtCantidad.setText(String.valueOf(Cantidad));
    }

    public String getSeleccionCombo() {
        return this.getJlCombo().getSelectedItem().toString();
    }    
        
}
