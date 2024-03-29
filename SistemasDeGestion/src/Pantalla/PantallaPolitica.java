/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaPolitica.java
 *
 * Created on 06-nov-2011, 20:03:53
 */
package Pantalla;

import Controlador.ControladorPoliticaSR;
import com.toedter.calendar.JDateChooser;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duende
 */
public class PantallaPolitica extends javax.swing.JInternalFrame {

    private String[] provedores = {" "};
    private DefaultTableModel dtm;
    private ControladorPoliticaSR objCont;

    /** Creates new form PantallaPolitica */
    public PantallaPolitica(ControladorPoliticaSR ctrl) {
        initComponents();
        objCont = ctrl;
        this.jdFecha.setDateFormatString(fechaDelSistema());
        //this.jdFecha.setEnabled(false);
        //**********************************************************************
        Object[][] data = new Object[0][5];
        String[] columnNames = {"Codigo", "Nombre", "Precio", "Stock Actual", "Lote"};
        dtm = new javax.swing.table.DefaultTableModel(data, columnNames);
        jtTabla.setModel(dtm);
        //**********************************************************************
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jcProveedores = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        jbEliminar = new javax.swing.JButton();
        jbAceptar = new javax.swing.JButton();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jbCrearPedido = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jcProductos = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Generación Manual de Pedidos");

        jLabel1.setText("Proveedores");

        jLabel2.setText("Fecha");

        jtTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtTabla);

        jbEliminar.setText("Eliminar");

        jbAceptar.setText("Calcular");

        jbCrearPedido.setText("Crear");

        jLabel3.setText("Productos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(72, 72, 72)
                            .addComponent(jcProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbCrearPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jbEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jbAceptar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jbAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(jbCrearPedido)
                        .addGap(18, 18, 18)
                        .addComponent(jbEliminar)
                        .addGap(177, 177, 177))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCrearPedido;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JComboBox jcProductos;
    private javax.swing.JComboBox jcProveedores;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JTable jtTabla;
    // End of variables declaration//GEN-END:variables

    public JButton getJbAceptar() {
        return jbAceptar;
    }

    public void setJbAceptar(JButton jbAceptar) {
        this.jbAceptar = jbAceptar;
    }

    public JButton getJbEliminar() {
        return jbEliminar;
    }

    public void setJbEliminar(JButton jbEliminar) {
        this.jbEliminar = jbEliminar;
    }

    public JComboBox getJcProveedores() {
        return jcProveedores;
    }

    public void setJcProveedores(JComboBox jcProveedores) {
        this.jcProveedores = jcProveedores;
    }

    public JDateChooser getJdFecha() {
        return jdFecha;
    }

    public void setJdFecha(JDateChooser jdFecha) {
        this.jdFecha = jdFecha;
    }

    public JTable getJtTabla() {
        return jtTabla;
    }

    public void setJtTabla(JTable jtTabla) {
        this.jtTabla = jtTabla;
    }

    public JButton getJbCrearPedido() {
        return jbCrearPedido;
    }

    public void setJbCrearPedido(JButton jbCrearPedido) {
        this.jbCrearPedido = jbCrearPedido;
    }

    private String fechaDelSistema() {
        String fechaSistema;
        GregorianCalendar fechaActual = new GregorianCalendar();
        fechaSistema = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH)).concat("-").concat(String.valueOf(fechaActual.get(GregorianCalendar.MONTH) + 1)).concat("-").concat(String.valueOf(fechaActual.get(GregorianCalendar.YEAR)));//AÃ±o...
        return fechaSistema;
    }

    public void Aceptar() {        
        String[][] datos;
        if (jtTabla.getRowCount() >= 1) {
            datos = new String[dtm.getRowCount()][2];
            for (int i = 0; i < dtm.getRowCount(); i++) {
                datos[i][0] = String.valueOf(dtm.getValueAt(i, 0));
                datos[i][1] = String.valueOf(dtm.getValueAt(i, 4));
            }
            //objCont.setPedidoManual(datos);
          //  objCont.cerrarGUI();
        }
    }

    public void Eliminar() {
        if (jtTabla.getSelectedRow() >= 0) { //Verifico que se seleccione una Fila de la Tabla...
            int valor = jtTabla.getSelectedRow();
            dtm.removeRow(valor);
        } else if (dtm.getRowCount() - 1 >= 0) {//Si no hay filas seleccionadas...
            dtm.removeRow(dtm.getRowCount() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "No Existen Filas...", "CUIDADO", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void AccionCombo() {
        //AcciÃ³n de ComboBox...
        //......................................................................
        Object[][] data = new Object[0][5];
        String[] columnNames = {"Codigo", "Nombre", "Precio", "Stock Actual", "Lote"};
        dtm = new javax.swing.table.DefaultTableModel(data, columnNames);
        jtTabla.setModel(dtm);
        //......................................................................
        Object[][] temp = objCont.getProductosPrev(jcProveedores.getSelectedIndex());
        Object[] newRow = new Object[5];
        for (int i = 0; i < temp.length; i++) {
            newRow[0] = temp[i][0];
            newRow[1] = temp[i][1];
            newRow[2] = temp[i][2];
            newRow[3] = temp[i][3];
            newRow[4] = temp[i][4];
            agregarProducto(newRow);
        }
    }

    public void agregarProducto(Object[] fila) {
        Object[] newRow = fila;
        dtm.addRow(newRow);
    }

    public void setProveedores(String[] vecProv) {
        this.provedores = vecProv;
        jcProveedores.setModel(new javax.swing.DefaultComboBoxModel(provedores));
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public JComboBox getJcProductos() {
        return jcProductos;
    }

    public void setJcProductos(JComboBox jcProductos) {
        this.jcProductos = jcProductos;
    }
    
    
}
