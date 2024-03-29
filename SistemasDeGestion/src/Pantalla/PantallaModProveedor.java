/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaModProveedor.java
 *
 * Created on 10-sep-2011, 20:32:39
 */
package Pantalla;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author duende
 */
public class PantallaModProveedor extends javax.swing.JInternalFrame {

    /** Creates new form PantallaModProveedor */
    public PantallaModProveedor() {
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

        jLabel1 = new javax.swing.JLabel();
        jlNombreProveedor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlCuitProveedor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlDireccionProveedor = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlTelProveedor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlEmailProveedor = new javax.swing.JLabel();
        btAceptar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jlMensaje = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre Proveedor:");

        jlNombreProveedor.setText("Nombre");

        jLabel2.setText("CUIT:");

        jlCuitProveedor.setText("jLabel3");

        jLabel3.setText("Dirección:");

        jlDireccionProveedor.setText("jLabel4");

        jLabel4.setText("Telefóno:");

        jlTelProveedor.setText("jLabel5");

        jLabel5.setText("Email:");

        jlEmailProveedor.setText("jLabel6");

        btAceptar.setText("Aceptar");

        btCancelar.setText("Cancelar");

        jlMensaje.setText("El proveedor que intenta modificar/eliminar es:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btAceptar)
                        .addGap(29, 29, 29)
                        .addComponent(btCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlMensaje))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEmailProveedor)
                            .addComponent(jlTelProveedor)
                            .addComponent(jlDireccionProveedor)
                            .addComponent(jlCuitProveedor)
                            .addComponent(jlNombreProveedor))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlMensaje)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlNombreProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlCuitProveedor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlDireccionProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jlTelProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jlEmailProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAceptar)
                    .addComponent(btCancelar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jlCuitProveedor;
    private javax.swing.JLabel jlDireccionProveedor;
    private javax.swing.JLabel jlEmailProveedor;
    private javax.swing.JLabel jlMensaje;
    private javax.swing.JLabel jlNombreProveedor;
    private javax.swing.JLabel jlTelProveedor;
    // End of variables declaration//GEN-END:variables

    public String getJlCuitProveedor() {
        return jlCuitProveedor.getText();
    }

    public void setJlCuitProveedor(String Cuit) {
        this.jlCuitProveedor.setText(Cuit);
    }

    public String getJlDireccionProveedor() {
        return jlDireccionProveedor.getText();
    }

    public void setJlDireccionProveedor(String Direccion) {
        this.jlDireccionProveedor.setText(Direccion);
    }

    public String getJlEmailProveedor() {
        return jlEmailProveedor.getText();
    }

    public void setJlEmailProveedor(String EmailProveedor) {
        this.jlEmailProveedor.setText(EmailProveedor);
    }

    public String getJlNombreProveedor() {
        return jlNombreProveedor.getText();
    }

    public void setJlNombreProveedor(String NombreProveedor) {
        this.jlNombreProveedor.setText(NombreProveedor);
    }

    public String getJlTelProveedor() {
        return jlTelProveedor.getText();
    }

    public void setJlTelProveedor(String TelProveedor) {
        this.jlTelProveedor.setText(TelProveedor);
    }

    public JButton getBtAceptar() {
        return btAceptar;
    }

    public void setBtAceptar(JButton btAceptar) {
        this.btAceptar = btAceptar;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public void setBtCancelar(JButton btCancelar) {
        this.btCancelar = btCancelar;
    }

    public void setJlMensaje(String Mensaje) {
        this.jlMensaje.setText(Mensaje);
    }
    
    

}
